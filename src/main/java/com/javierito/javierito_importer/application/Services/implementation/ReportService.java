package com.javierito.javierito_importer.application.Services.implementation;


import com.javierito.javierito_importer.application.Services.interfaces.IReportService;
import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.ports.IReportDomainRepository;
import lombok.RequiredArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@RequiredArgsConstructor
public class ReportService implements IReportService {

    private final IReportDomainRepository reportDomainRepository;

    @Override
    public List<Report> getReports(int limit, int offset, String param, Timestamp startDate, Timestamp endDate, String order) {
        var result = reportDomainRepository.getAllReports(limit, offset, param, startDate, endDate, order);
        return result;
    }
}
