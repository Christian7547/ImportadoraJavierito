package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Report;

import java.sql.Timestamp;
import java.util.List;

public interface IReportDomainRepository {

    List<Report> getAllReports(int limit, int offset, String param, Timestamp startDate, Timestamp endDate, String order);

}
