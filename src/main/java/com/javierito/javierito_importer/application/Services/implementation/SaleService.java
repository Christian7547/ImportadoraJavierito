package com.javierito.javierito_importer.application.Services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.models.Sale;
import com.javierito.javierito_importer.domain.models.SaleDetail;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleDomainRepository saleDomainRepository;
    private final JsonConverter jsonConverter;

    @Override
    public long createSale(Sale sale) throws JsonProcessingException {
        double total = sale.getDetails().stream().mapToDouble(SaleDetail::getSubtotal).sum();
        sale.setTotal(total);
        double calculateCommission = total * 0.1;
        sale.setCommission(calculateCommission);
        double calculateDiscount = 0;
        sale.setDiscount(calculateDiscount);
        String serializeDetails = jsonConverter.serializeCollection(sale.getDetails());
        return saleDomainRepository.createSale(sale.getTotal(),
                sale.getEmployeeID(),
                sale.getClientID(),
                sale.getCommission(),
                sale.getDiscount(),
                serializeDetails);
    }
}
