package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Sale;

public interface ISaleDomainRepository {
    long createSale (double total, long employeeId, long clientId, double commission, double discount, String details);
}
