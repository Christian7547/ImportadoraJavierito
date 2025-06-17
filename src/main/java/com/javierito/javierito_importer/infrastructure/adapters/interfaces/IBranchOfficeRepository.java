package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.NativeQuery;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.lang.annotation.Native;
import java.util.List;
import java.util.Optional;

public interface IBranchOfficeRepository extends JpaRepository<BranchOfficeEntity, Integer> {

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status < 2")
    long countBranchOffices();

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status = 0")
    long countInactivesBranchOffices();

    @Query("SELECT COUNT(b.id) FROM BranchOfficeEntity b WHERE b.status = 1")
    long countActivesBranchOffices();

    @NativeQuery("SELECT COUNT(I.id) " +
            "FROM \"Item\" I " +
            "INNER JOIN \"Stock\" S ON S.\"itemID\" = I.id " +
            "WHERE S.\"branchOfficeID\" = :officeId")
    long countItemsByOfficeId(@Param("officeId") int officeId);
}
