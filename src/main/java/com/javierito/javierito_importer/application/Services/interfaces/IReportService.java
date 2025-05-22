package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import org.yaml.snakeyaml.util.Tuple;

import java.sql.Timestamp;
import java.util.List;

public interface IReportService {

    List<Report> getReports(int limit, int offset, String param, Timestamp startDate, Timestamp endDate, String order);

    int insertReport(InsertReport report);

    Long countAll();
    Long countAllSales();
    Long countAllInventory();

    void deleteReport(Long reportId);
}
