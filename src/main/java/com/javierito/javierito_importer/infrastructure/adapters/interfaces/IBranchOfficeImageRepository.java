package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchOfficeImageRepository extends JpaRepository<BranchOfficeImageEntity, Integer> {
}
