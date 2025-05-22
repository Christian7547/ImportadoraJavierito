package com.javierito.javierito_importer.infrastructure.adapters.interfaces;

import com.javierito.javierito_importer.infrastructure.entities.ReportEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IReportsRepository extends JpaRepository<ReportEntity, Integer> {
    @Query("SELECT COUNT(r.id) FROM ReportEntity r ")
    Long countAll();

    @Query("SELECT COUNT(r.id) FROM ReportEntity r WHERE r.reportType IN ('Inventario.csv','Inventario.xlsx')")
    Long countAllSales();

    @Query("SELECT COUNT(r.id) FROM ReportEntity r WHERE r.reportType IN ('Ventas.csv','Ventas.xlsx')")
    Long countAllInventory();

    @Modifying
    @Transactional
    @Query("DELETE FROM ReportEntity r WHERE r.id = :reportId")
    int deleteByReportId(@Param("reportId") Long reportId);
}
