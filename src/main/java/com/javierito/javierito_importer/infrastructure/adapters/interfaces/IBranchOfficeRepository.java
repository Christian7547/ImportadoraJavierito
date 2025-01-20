package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranchOfficeRepository extends JpaRepository<BranchOfficeEntity, Integer> {
}
