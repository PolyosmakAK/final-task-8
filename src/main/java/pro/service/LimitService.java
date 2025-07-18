package pro.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
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

    public ResponseEntity<LimitResponse> reservationLimitByUsers(LimitDto limitDto) {
        long id = limitDto.id();
        float reservation = limitDto.reservation();
        UsersLimitEntity usersLimit = usersLimitService.getLimitById(id);
        Float currentLimit = usersLimit.getCurrentLimit();
        if (currentLimit >= reservation) {
            UsersLimitEntity newUsersLimitEntity = new UsersLimitEntity();
            newUsersLimitEntity.setId(id);
            newUsersLimitEntity.setDayLimit(usersLimit.getDayLimit());
            newUsersLimitEntity.setCurrentLimit(usersLimit.getCurrentLimit() - reservation);
            newUsersLimitEntity.setLastReservation(reservation);
            usersLimitService.saveNewLimitsParams(newUsersLimitEntity);
            return ResponseEntity.ok(new LimitResponse("Резервация выполнена успешно"));
        } else {
            throw new LimitException(currentLimit, reservation);
        }
    }

    public void rollbackReservationLimitById(Long id) {
        UsersLimitEntity usersLimit = usersLimitService.getLimitById(id);
        Float lastReservation = usersLimit.getLastReservation();
        Float currentLimit = usersLimit.getCurrentLimit();
        if (lastReservation != null && !currentLimit.equals(Float.valueOf(configurationLimit.getDayLimit()))) {
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
