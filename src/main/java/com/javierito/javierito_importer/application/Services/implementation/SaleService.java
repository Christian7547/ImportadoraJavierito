package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.ISaleRepository;

import java.time.LocalDateTime;
import java.util.List;

public class SaleService implements ISaleService {

    private final ISaleDomainRepository saleDomainRepository;

    public SaleService(ISaleDomainRepository saleDomainRepository) {
        this.saleDomainRepository = saleDomainRepository;
    }

    @Override
    public List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to) {
        return saleDomainRepository.getSalesReport(from, to);
    }

}
