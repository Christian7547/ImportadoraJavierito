package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    Page<UserEntity> findAll(Pageable pageable);
    Optional<UserEntity> findByEmail(String email);
}
