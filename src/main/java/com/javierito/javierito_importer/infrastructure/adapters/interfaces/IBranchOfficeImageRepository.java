package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;
import java.util.List;

public interface IBranchOfficeImageRepository extends JpaRepository<BranchOfficeImageEntity, Integer> {
    @Query("SELECT new BranchOfficeImageEntity(b.id, b.pathBranchOffice) FROM BranchOfficeImageEntity b WHERE b.branchOfficeID = :branchOfficeId")
    List<BranchOfficeImageEntity> getImagesByBranchOfficeId(int branchOfficeId);

    boolean existsByPathBranchOffice(String path);
}
