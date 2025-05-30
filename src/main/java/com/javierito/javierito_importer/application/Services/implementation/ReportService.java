package com.javierito.javierito_importer.application.Services.implementation;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.BarcodeModels.exception.ReportNotFoundException;
import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.ports.IReportDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IReportDomainRepository reportDomainRepository;

    @Override
    public List<Report> getReports(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order) {

        if (startDate != null) {
            startDate.minusDays(1);
        } else if (endDate != null) {
            endDate.plusDays(1);
        }
        var result = reportDomainRepository.getAllReports(limit, offset, param, startDate, endDate, order);

        return result;
    }

    @Override
    public List<Report> getAllReportSales(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order) {
        if (startDate != null) {
            startDate.minusDays(1);
        } else if (endDate != null) {
            endDate.plusDays(1);
        }
        var result = reportDomainRepository.getAllReportSales(limit, offset, param, startDate, endDate, order);

        return result;
    }

    @Override
    public List<Report> getAllReporInventories(int limit, int offset, String param, LocalDateTime startDate, LocalDateTime endDate, String order) {
        if (startDate != null) {
            startDate.minusDays(1);
        } else if (endDate != null) {
            endDate.plusDays(1);
        }
        var result = reportDomainRepository.getAllReporInventories(limit, offset, param, startDate, endDate, order);

        return result;
    }

    @Override
    public int insertReport(InsertReport report) {

        var result = reportDomainRepository.insertReport(report);

        return result;
    }

    @Override
    public Long countAll() {
        return reportDomainRepository.countAll();
    }

    @Override
    public Long countAllSales() {
        return reportDomainRepository.countAllSales();
    }

    @Override
    public Long countAllInventory() {
        return reportDomainRepository.countAllInventory();
    }

    @Override
    public void deleteReport(Long reportId) {
        int result = reportDomainRepository.deleteReport(reportId);
        if (result == 0)
            throw new ReportNotFoundException("Report Not Found: {reportId: ]" + reportId );
    }
}
