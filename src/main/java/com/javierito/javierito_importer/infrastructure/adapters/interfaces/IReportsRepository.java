package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IReportsRepository extends JpaRepository<ReportEntity, Integer> {
    @Query("SELECT COUNT(r.id) FROM ReportEntity r ")
    Long countAll();

    @Query("SELECT COUNT(r.id) FROM ReportEntity r WHERE r.reportType IN ('Inventario.csv','Inventario.xlsx')")
    Long countAllSales();

    @Query("SELECT COUNT(r.id) FROM ReportEntity r WHERE r.reportType IN ('Ventas.csv','Ventas.xlsx')")
    Long countAllInventory();
}
