package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.BarcodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IBarcodeRepository extends JpaRepository<BarcodeEntity, Long> {
    Optional<BarcodeEntity> getByBarcode(String barcode);
}
