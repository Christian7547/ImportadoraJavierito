package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.InsertReport;
import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.ports.IReportDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IReportsRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ReportMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository implements IReportDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReportMapper reportMapper;

    private final IReportsRepository reportRepository;

    @Override
    public List<Report> getAllReports(int limit,
                                      int offset,
                                      @Nullable String param,
                                      @Nullable LocalDateTime startDate,
                                      @Nullable LocalDateTime endDate,
                                      @Nullable String order) {
        String sql = "SELECT * FROM ufc_get_reports(:p_limit, :p_offset, :p_param, :p_start_date, :p_end_date, :p_order)";

        Query query = entityManager.createNativeQuery(sql, Report.class);
        query.setParameter("p_limit", limit);
        query.setParameter("p_offset", offset);
        query.setParameter("p_param", param);
        query.setParameter("p_start_date", startDate);
        query.setParameter("p_end_date", endDate);
        query.setParameter("p_order", order);

        List<Report> reports = query.getResultList();
        return reports.isEmpty() ? new ArrayList<>() : reports;
    }

    @Override
    public List<Report> getAllReportSales(int limit, int offset, @Nullable String param, @Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate, @Nullable String order) {
        String sql = "SELECT * FROM ufc_get_report_sales(:p_limit, :p_offset, :p_param, :p_start_date, :p_end_date, :p_order)";

        Query query = entityManager.createNativeQuery(sql, Report.class);
        query.setParameter("p_limit", limit);
        query.setParameter("p_offset", offset);
        query.setParameter("p_param", param);
        query.setParameter("p_start_date", startDate);
        query.setParameter("p_end_date", endDate);
        query.setParameter("p_order", order);

        List<Report> reports = query.getResultList();
        return reports.isEmpty() ? new ArrayList<>() : reports;
    }

    @Override
    public List<Report> getAllReporInventories(int limit, int offset, @Nullable String param, @Nullable LocalDateTime startDate, @Nullable LocalDateTime endDate, @Nullable String order) {
        String sql = "SELECT * FROM ufc_get_report_inventory(:p_limit, :p_offset, :p_param, :p_start_date, :p_end_date, :p_order)";

        Query query = entityManager.createNativeQuery(sql, Report.class);
        query.setParameter("p_limit", limit);
        query.setParameter("p_offset", offset);
        query.setParameter("p_param", param);
        query.setParameter("p_start_date", startDate);
        query.setParameter("p_end_date", endDate);
        query.setParameter("p_order", order);

        List<Report> reports = query.getResultList();
        return reports.isEmpty() ? new ArrayList<>() : reports;
    }

    @Override
    public int insertReport(InsertReport report) {
        StoredProcedureQuery query = entityManager
                .createStoredProcedureQuery("usp_insert_report");

        query.registerStoredProcedureParameter("p_name", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_path_report", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("p_report_type", String.class, ParameterMode.IN);
        query.registerStoredProcedureParameter("affected_rows", Integer.class, ParameterMode.OUT);


        query.setParameter("p_name", report.getName());
        query.setParameter("p_path_report", report.getPathReport());
        query.setParameter("p_user_id", report.getUserId());
        query.setParameter("p_report_type", report.getReportType());

        query.execute();

        Integer affectedRows = (Integer) query.getOutputParameterValue("affected_rows");

        report.setAffectedRows(affectedRows);

        return affectedRows;
    }

    @Override
    public int deleteReport(Long reportId) {
        return reportRepository.deleteByReportId(reportId);
    }

    @Override
    public Long countAll() {
        return reportRepository.countAll();
    }

    @Override
    public Long countAllSales() {
        return reportRepository.countAllSales();
    }

    @Override
    public Long countAllInventory() {
        return reportRepository.countAllInventory();
    }


}
