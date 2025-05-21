package com.javierito.javierito_importer.application.Services.implementation;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.ports.IReportDomainRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.sql.Timestamp;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IReportDomainRepository reportDomainRepository;

    @Override
    public List<Report> getReports(int limit, int offset, String param, Timestamp startDate, Timestamp endDate, String order) {

        var result = reportDomainRepository.getAllReports(limit, offset, param, startDate, endDate, order);

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
}
