package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.SaleDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {
    void deleteBySaleId(long saleId);
    Optional<SaleDetailEntity> getBySaleId(long saleId);
}
