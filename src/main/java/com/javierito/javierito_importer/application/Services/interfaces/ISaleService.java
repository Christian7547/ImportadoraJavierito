package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.SaleModels.*;

import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface ISaleService {
    long createSale(Sale sale) throws JsonProcessingException;

    List<SaleList> getAll(int limit,
                          int offset,
                          LocalDateTime initDate,
                          LocalDateTime finishDate,
                          String params);

    List<SaleReport> getSalesReport(LocalDateTime from, LocalDateTime to) throws JsonProcessingException;

    Sale getSaleById(long id);

    boolean deleteSale(long id, short newStatus);

    void refund(long saleId) throws JsonProcessingException;

    SaleDetail getDetailsBySaleId(long saleId);
}
