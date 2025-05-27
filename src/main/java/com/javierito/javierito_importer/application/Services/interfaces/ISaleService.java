package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.SaleModels.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.lang.Nullable;

public interface ISaleService {
    long createSale(Sale sale) throws JsonProcessingException;

    List<SaleList> getAll(int limit,
                          int offset,
                          @Nullable LocalDateTime initDate,
                          @Nullable LocalDateTime finishDate,
                          @Nullable String params);

    Long countAll();

    List<SaleReport> getSalesReport(LocalDateTime from, LocalDateTime to) throws JsonProcessingException;

    Sale getSaleById(long id);

    boolean deleteSale(long id, short newStatus);

    void refund(long saleId) throws JsonProcessingException;

    SaleDetail getDetailsBySaleId(long saleId);

    boolean changeStatus(long saleId);

    SingleSaleWithDetails getSaleWithDetails(long id) throws JsonProcessingException;

    BigDecimal getTotalInDateRange(@Nullable LocalDateTime startDate, @Nullable LocalDateTime finishDate);

    Long getSoldItemsInDateRange(LocalDateTime startDate, LocalDateTime finishDate);
}
