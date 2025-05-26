package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Optional<UserEntity> findByEmail(String email);

    @Query("SELECT COUNT(*) FROM UserEntity u WHERE u.status > 0")
    long count();

    @Query("SELECT COUNT(*) FROM UserEntity u WHERE u.status = 1")
    long countActiveUsers();

    @Query("SELECT COUNT(*) FROM UserEntity u WHERE u.status = 2")
    long countInactiveUsers();
}
