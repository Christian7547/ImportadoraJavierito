package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBranchOfficeRepository extends JpaRepository<BranchOfficeEntity, Integer> {

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status < 2")
    long countBranchOffices();

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status = 0")
    long countInactivesBranchOffices();

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status = 1")
    long countActivesBranchOffices();
}
