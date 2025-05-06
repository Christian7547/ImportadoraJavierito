package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.SaleModels.SaleDetail;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleList;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.domain.models.SaleModels.Sale;

public interface ISaleService {
    long createSale(Sale sale) throws JsonProcessingException;

    List<SaleList> getAll(int limit,
                          int offset,
                          LocalDateTime initDate,
                          LocalDateTime finishDate,
                          String params);

    List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to);

    Sale getSaleById(long id);

    Sale deleteSale(long id, short newStatus);

    boolean refund(long saleId) throws JsonProcessingException;

    SaleDetail getDetailsBySaleId(long saleId);
}
