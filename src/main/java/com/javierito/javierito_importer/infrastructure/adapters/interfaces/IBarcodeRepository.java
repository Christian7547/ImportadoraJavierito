package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBarcodeRepository extends JpaRepository<BarcodeEntity, Long> {
}
