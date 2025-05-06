package com.javierito.javierito_importer.application.Services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.domain.models.SaleModels.*;
import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.domain.ports.IBarcodeDomainRepository;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleDomainRepository saleDomainRepository;
    private final IBarcodeDomainRepository barcodeDomainRepository;
    private final IStockDomainRepository stockDomainRepository;
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

    @Override
    public Sale getSaleById(long id) {
        return saleDomainRepository.getSaleById(id);
    }

    @Override
    @Transactional
    public Sale deleteSale(long id, short newStatus) {
        Sale getSale = getSaleById(id);
        if(getSale == null )
            return null;
        getSale.setStatus(newStatus);
        if(newStatus == 2){
            saleDomainRepository.deleteDetailBySaleId(getSale.getId());
        }
        return saleDomainRepository.saveSale(getSale);
    }

    @Override
    @Transactional
    public boolean refund(long saleId) throws JsonProcessingException {
        SaleDetail saleDetail = getDetailsBySaleId(saleId);
        List<Detail> details = jsonConverter.deserializeCollection(
                saleDetail.getDetail(),
                Detail.class
        );
        List<Barcode> barcodes = barcodeDomainRepository.getManyBarcodesByCodes(details.stream().map(Detail::getBarcode).toList());
        barcodes.forEach(barcode -> barcode.setStatus((short) 1));
        for (Barcode barcode : barcodes) {
            Stock currentStock = stockDomainRepository.getStock(barcode.getStockId());
            if(currentStock == null) {
                continue;
            }
            int currentQuantityByStock = currentStock.getQuantity();
            currentStock.setQuantity(currentQuantityByStock + 1);
            stockDomainRepository.editStock(currentStock);
        }
        barcodeDomainRepository.saveManyChanges(barcodes);
        deleteSale(saleId, (short) 2);
        return false;
    }

    @Override
    public SaleDetail getDetailsBySaleId(long saleId) {
        return saleDomainRepository.getDetailsBySaleId(saleId);
    }

}
