package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface IReportDomainRepository {

    List<Report> getAllReports(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);
    List<Report> getAllReportSales(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);
    List<Report> getAllReporInventories(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);

    int insertReport(InsertReport report);

    int deleteReport(Long reportId);

    Long countAll();
    Long countAllSales();
    Long countAllInventory();


}
