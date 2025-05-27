package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import org.yaml.snakeyaml.util.Tuple;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

public interface IReportService {

    List<Report> getReports(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);
    List<Report> getAllReportSales(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);
    List<Report> getAllReporInventories(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order);


    int insertReport(InsertReport report);

    Long countAll();
    Long countAllSales();
    Long countAllInventory();

    void deleteReport(Long reportId);
}
