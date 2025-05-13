package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;

import java.sql.Timestamp;
import java.util.List;

public interface IReportService {

    List<Report> getReports(int limit, int offset, String param, Timestamp startDate, Timestamp endDate, String order);

    int insertReport(InsertReport report);
}
