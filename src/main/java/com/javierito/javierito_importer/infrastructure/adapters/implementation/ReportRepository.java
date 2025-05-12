package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Report;
import com.javierito.javierito_importer.domain.ports.IReportDomainRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ReportMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ReportRepository implements IReportDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ReportMapper reportMapper;

    @Override
    public List<Report> getAllReports(int limit,
                                      int offset,
                                      @Nullable String param,
                                      @Nullable Timestamp startDate,
                                      @Nullable Timestamp endDate,
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
}
