package com.javierito.javierito_importer.application.Services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleList;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleDomainRepository saleDomainRepository;
    private final JsonConverter jsonConverter;

    public List<SaleList> getAll(int limit,
                                 int offset,
                                 LocalDateTime initDate,
                                 LocalDateTime finishDate,
                                 String params) {
        Pageable pageable = PageRequest.of(offset, limit);
        finishDate = finishDate.plusDays(1);
        initDate = initDate.minusDays(1);
        return saleDomainRepository.getAll(pageable, initDate, finishDate, params);
    }

    @Override
    public long createSale(Sale sale) throws JsonProcessingException {
        String serializeDetails = jsonConverter.serializeCollection(sale.getDetails());
        return saleDomainRepository.createSale(sale.getTotal(),
                sale.getEmployeeID(),
                sale.getClientID(),
                sale.getPercentageDiscount(),
                serializeDetails);
    }

    public List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to) {
        return saleDomainRepository.getSalesReport(from, to);
    }
}
