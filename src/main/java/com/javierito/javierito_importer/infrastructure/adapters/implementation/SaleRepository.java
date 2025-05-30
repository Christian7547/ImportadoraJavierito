package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleDetail;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleList;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.ISaleDetailRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.ISaleRepository;
import com.javierito.javierito_importer.infrastructure.entities.SaleDetailEntity;
import com.javierito.javierito_importer.infrastructure.entities.SaleEntity;
import com.javierito.javierito_importer.infrastructure.mappers.SaleMapper;
import jakarta.persistence.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class SaleRepository implements ISaleDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private SaleMapper saleMapper;

    private final ISaleRepository saleRepository;
    private final ISaleDetailRepository saleDetailRepository;

    private ObjectMapper objectMapper;

    @Override
    public List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to) {
        String sql = "SELECT * FROM ufc_get_sales_details(?, ?)";

        Query results = entityManager.createNativeQuery(sql, SalesDetails.class)
                .setParameter(1, from)
                .setParameter(2, to);

        List<SalesDetails> reports = results.getResultList();
        
        return reports;
    }

    @Override
    public Sale saveSale(Sale source) {
        SaleEntity entity = saleMapper.toEntityFromSale(source);
        var result = saleRepository.save(entity);
        return saleMapper.toSaleFromEntity(result);
    }

    @Override
    public Sale getSaleById(long id) {
        return saleMapper.toSaleFromEntity(saleRepository.findById(id).get());
    }

    @Override
    public void deleteDetailBySaleId(long saleId) {
        saleDetailRepository.deleteBySaleId(saleId);
    }

    @Override
    public boolean refund(long saleId, String[] barcodes) {
        StoredProcedureQuery procedure = entityManager.createStoredProcedureQuery("usp_refund");

        procedure.registerStoredProcedureParameter("p_saleId", Long.class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_barcodes", String[].class, ParameterMode.IN);
        procedure.registerStoredProcedureParameter("p_success", Boolean.class, ParameterMode.OUT);

        procedure.setParameter("p_saleId", saleId);
        procedure.setParameter("p_barcodes", barcodes);
        procedure.execute();

        return (Boolean) procedure.getOutputParameterValue("p_success");
    }

    @Override
    public String getSaleWithDetails(long id) {
        String sql = "SELECT * FROM ufc_get_sale_by_id(:p_id)";

        Query query = entityManager.createNativeQuery(sql, String.class);
        query.setParameter("p_id", id);
        return query.getSingleResult().toString();
    }

    @Override
    public SaleDetail getDetailsBySaleId(long saleId) {
        Optional<SaleDetailEntity> entity = saleDetailRepository.getBySaleId(saleId);
        if(entity.isEmpty()){
            return null;
        }
        return saleMapper.toSaleDetail(entity.get());
    }

    @Override
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

    @Override
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
    public BigDecimal getTotalInDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        String sql = "SELECT * FROM ufc_get_total_sales_in_date_range(:p_start_date, :p_finish_date)";
        Query query = entityManager.createNativeQuery(sql, BigDecimal.class);
        query.setParameter("p_start_date", startDate);
        query.setParameter("p_finish_date", finishDate);
        return (BigDecimal) query.getSingleResult();
    }

    @Override
    public Long getSoldItemsInDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        String sql = "SELECT * FROM ufc_get_sold_items_in_date_range(:p_start_date, :p_finish_date)";
        Query query = entityManager.createNativeQuery(sql, Long.class);
        query.setParameter("p_start_date", startDate);
        query.setParameter("p_finish_date", finishDate);
        return (Long) query.getSingleResult();
    }

    @Override
    public Long countAll() {
        return saleRepository.countAllSales();
    }
}
