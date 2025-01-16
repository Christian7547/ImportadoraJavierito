package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.StockEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IStockRepository extends JpaRepository<StockEntity, Long> {
}
