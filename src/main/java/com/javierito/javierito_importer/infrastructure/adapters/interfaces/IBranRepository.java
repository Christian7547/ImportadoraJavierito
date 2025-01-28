package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BrandEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBranRepository extends JpaRepository<BrandEntity, Integer> {
}
