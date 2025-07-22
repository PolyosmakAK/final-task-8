package pro.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pro.entity.UsersLimitEntity;
import pro.repository.UsersLimitRepository;

@Service
@AllArgsConstructor
public class UsersLimitService {

    private final UsersLimitRepository usersLimitRepository;

    /**
     * Установка значения лимита для всех пользователей
     *
     * @param limit значение лимита
     */
    public void updateLimits(Double limit) {
        usersLimitRepository.updateAllLimits(limit);
    }

    /**
     * Получение лимитов для выбранного пользователя
     *
     * @param id идентификатор пользователя
     * @return объект UsersLimitEntity
     */
    public UsersLimitEntity getLimitById(Long id) {
        return usersLimitRepository.getUsersLimitEntitiesById(id).orElseGet(() -> {
            UsersLimitEntity usersLimit = new UsersLimitEntity(id, 10000.00, 10000.00, null);
            usersLimitRepository.save(usersLimit);
            return usersLimit;
        });
    }

    /**
     * Сохранение новых значений лимита для пользователя
     *
     * @param usersLimitEntity объект UsersLimitEntity
     */
    public void saveNewLimitsParams(UsersLimitEntity usersLimitEntity) {
        usersLimitRepository.save(usersLimitEntity);
    }
}
