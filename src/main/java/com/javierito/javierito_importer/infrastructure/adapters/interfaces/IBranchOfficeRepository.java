package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface IBranchOfficeRepository extends JpaRepository<BranchOfficeEntity, Integer> {
    @Query("SELECT new BranchOfficeEntity(b.id, b.name, b.address, b.latitude, b.longitude, b.registerDate) FROM BranchOfficeEntity b WHERE b.id = :id")
    Optional<BranchOfficeEntity> getBranchOfficeById(int id);
}
