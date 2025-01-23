package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.ArrayList;

public interface IBranchOfficeImageRepository extends JpaRepository<BranchOfficeImageEntity, Integer> {
    @Query("SELECT b.pathBranchOffice FROM BranchOfficeImageEntity b WHERE b.branchOfficeID = :branchOfficeId")
    ArrayList<BranchOfficeImageEntity> getImagesByBranchOfficeId(int branchOfficeId);
}
