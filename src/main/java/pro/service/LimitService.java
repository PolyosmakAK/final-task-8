package pro.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.config.ConfigurationLimit;
import pro.dto.LimitDto;
import pro.entity.UsersLimitEntity;
import pro.exception.LimitException;
import pro.exception.RollbackReservationException;
import pro.response.LimitResponse;

@Service
@AllArgsConstructor
public class LimitService {
    private final UsersLimitService usersLimitService;
    private final ConfigurationLimit configurationLimit;

    public LimitResponse reservationLimitByUsers(LimitDto limitDto) {
        long id = limitDto.id();
        Double reservation = limitDto.reservation();
        UsersLimitEntity usersLimit = usersLimitService.getLimitById(id);
        Double currentLimit = usersLimit.getCurrentLimit();
        if (currentLimit >= reservation) {
            UsersLimitEntity newUsersLimitEntity = new UsersLimitEntity();
            newUsersLimitEntity.setId(id);
            newUsersLimitEntity.setDayLimit(usersLimit.getDayLimit());
            newUsersLimitEntity.setCurrentLimit(usersLimit.getCurrentLimit() - reservation);
            newUsersLimitEntity.setLastReservation(reservation);
            usersLimitService.saveNewLimitsParams(newUsersLimitEntity);
            return new LimitResponse("Резервация выполнена успешно");
        } else {
            throw new LimitException(currentLimit, reservation);
        }
    }

    public void rollbackReservationLimitById(Long id) {
        UsersLimitEntity usersLimit = usersLimitService.getLimitById(id);
        Double lastReservation = usersLimit.getLastReservation();
        Double currentLimit = usersLimit.getCurrentLimit();
        if (lastReservation != null && !currentLimit.equals(configurationLimit.getDayLimit())) {
            UsersLimitEntity newUsersLimitEntity = new UsersLimitEntity();
            newUsersLimitEntity.setId(id);
            newUsersLimitEntity.setDayLimit(usersLimit.getDayLimit());
            newUsersLimitEntity.setCurrentLimit(usersLimit.getCurrentLimit() + usersLimit.getLastReservation());
            usersLimitService.saveNewLimitsParams(newUsersLimitEntity);
        } else {
            throw new RollbackReservationException(lastReservation, currentLimit);
        }
    }
}
