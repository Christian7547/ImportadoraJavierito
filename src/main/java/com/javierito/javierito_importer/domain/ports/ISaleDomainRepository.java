package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;

import java.time.LocalDateTime;
import java.util.List;

public interface ISaleDomainRepository {
    long createSale (double total, long employeeId, long clientId, String details);

    List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to);
}
