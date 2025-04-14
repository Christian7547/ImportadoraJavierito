package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import java.time.LocalDateTime;
import java.util.List;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.domain.models.Sale;

public interface ISaleService {
    long createSale(Sale sale) throws JsonProcessingException;

    List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to);

}
