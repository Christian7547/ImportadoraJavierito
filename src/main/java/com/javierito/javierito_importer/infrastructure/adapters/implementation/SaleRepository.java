package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleList;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.ISaleRepository;
import com.javierito.javierito_importer.infrastructure.mappers.SaleMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SaleRepository implements ISaleDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SaleMapper saleMapper;

    private final ISaleRepository saleRepository;

    private ObjectMapper objectMapper;

    public SaleRepository(ISaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    public List<SaleList> getAll(Pageable pageable,
                                 @Nullable LocalDateTime initDate,
                                 @Nullable LocalDateTime finishDate,
                                 @Nullable String params){
        String sql = "SELECT * FROM ufc_get_sales(:p_limit, :p_offset, :p_initDate, :p_finishDate, :p_params)";
        Query query = entityManager.createNativeQuery(sql, SaleList.class);
        query.setParameter("p_limit", pageable.getPageSize());
        query.setParameter("p_offset", pageable.getPageNumber());
        query.setParameter("p_initDate", initDate);
        query.setParameter("p_finishDate", finishDate);
        query.setParameter("p_params", params);
        List<SaleList> sales = query.getResultList();

        if(sales.isEmpty()){
            return new ArrayList<>();
        }
        return sales;
    }

    @Override
    public List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to) {

        String sql = "SELECT * FROM ufc_get_sales_details(?, ?)";

        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, from)
                .setParameter(2, to)
                .getResultList();

        List<SalesDetails> reports = new ArrayList<>();

        for (Object[] result : results) {
            SalesDetails salesDetails = new SalesDetails();

            salesDetails.setSaleId(((Number) result[0]).longValue());
            salesDetails.setEmployeeFullName((String) result[1]);
            salesDetails.setClientFullName((String) result[2]);
            salesDetails.setSaleTotal((BigDecimal) result[3]);
            salesDetails.setSaleCommission((BigDecimal) result[4]);
            salesDetails.setSaleDiscount((BigDecimal) result[5]);
            salesDetails.setSaleDate(((java.sql.Timestamp) result[6]).toLocalDateTime());


            try {
                JsonNode saleDetailJson = objectMapper.readTree(result[7].toString());
                salesDetails.setSaleDetail(saleDetailJson);
            } catch (Exception e) {
                throw new RuntimeException("Error al parsear el campo sale_detail JSON", e);
            }
            reports.add(salesDetails);
        }

        return reports;
    }
    public long createSale(double total,
                           long employeeId,
                           long clientId,
                           BigDecimal percentageDiscount,
                           String details) {
        String sql = "SELECT * FROM ufc_new_sale(:p_total, :p_employeeid, :p_clientid, :p_percentage_discount, CAST(:p_detail AS JSONB));";

        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("p_total", BigDecimal.valueOf(total));
        query.setParameter("p_employeeid", employeeId);
        query.setParameter("p_clientid", clientId);
        query.setParameter("p_detail", details);
        query.setParameter("p_percentage_discount", percentageDiscount);
        Long out = (Long) query.getSingleResult();
        return out.longValue();
    }
}
