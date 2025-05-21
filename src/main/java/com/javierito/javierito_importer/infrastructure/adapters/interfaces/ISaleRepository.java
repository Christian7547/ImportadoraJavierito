package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.SaleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
    @Query("SELECT COUNT(id) FROM SaleEntity")
    Long countAllSales();
}
