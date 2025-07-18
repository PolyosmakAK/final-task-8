package pro.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pro.entity.UsersLimitEntity;

import java.util.Optional;

@Repository
public interface UsersLimitRepository extends JpaRepository<UsersLimitEntity, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE UsersLimitEntity u SET u.dayLimit = :limit, u.currentLimit = :limit")
    void updateAllLimits(@Param("limit") String limit);

    Optional<UsersLimitEntity> getUsersLimitEntitiesById(Long id);
}
