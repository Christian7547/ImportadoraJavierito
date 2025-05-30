package com.javierito.javierito_importer.application.Services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.*;
import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.ports.IBarcodeDomainRepository;
import com.javierito.javierito_importer.domain.ports.ISaleDomainRepository;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class SaleService implements ISaleService {

    private final ISaleDomainRepository saleDomainRepository;
    private final IBarcodeDomainRepository barcodeDomainRepository;
    private final IStockDomainRepository stockDomainRepository;
    private final JsonConverter jsonConverter;

    public List<SaleList> getAll(int limit,
                                 int offset,
                                 @Nullable LocalDateTime initDate,
                                 @Nullable LocalDateTime finishDate,
                                 String params) {
        Pageable pageable = PageRequest.of(offset, limit);
        if (initDate != null) {
            initDate.minusDays(1);
        } else if (finishDate != null) {
            finishDate.plusDays(1);
        }
        return saleDomainRepository.getAll(pageable, initDate, finishDate, params);
    }

    @Override
    public Long countAll() {
        return saleDomainRepository.countAll();
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

    public List<SaleReport> getSalesReport(LocalDateTime from, LocalDateTime to) throws JsonProcessingException {
        List<SalesDetails> data = saleDomainRepository.getSalesReport(from, to);
        List<SaleReport> reportList = new ArrayList<>();

        for (SalesDetails detail : data) {
            List<Detail> detailList = jsonConverter.deserializeCollection(detail.getSaleDetail(), Detail.class);

            SaleReport report = SaleReport.builder()
                    .saleId(detail.getSaleId())
                    .employeeFullName(detail.getEmployeeFullName())
                    .clientFullName(detail.getClientFullName())
                    .saleTotal(detail.getSaleTotal())
                    .saleDiscount(detail.getSaleDiscount())
                    .saleDate(detail.getSaleDate())
                    .saleDetail(detailList)
                    .build();

            reportList.add(report);
        }

        return reportList;
    }

    @Override
    public Sale getSaleById(long id) {
        return saleDomainRepository.getSaleById(id);
    }

    @Override
    @Transactional
    public boolean deleteSale(long id, short newStatus) {
        Sale getSale = getSaleById(id);
        if(getSale == null )
            return false;
        getSale.setStatus(newStatus);
        if(newStatus == 2){
            saleDomainRepository.deleteDetailBySaleId(getSale.getId());
        }
        return true;
    }

    @Override
    public void refund(long saleId) throws JsonProcessingException {
        SaleDetail saleDetail = getDetailsBySaleId(saleId);
        List<Detail> details = jsonConverter.deserializeCollection(
                saleDetail.getDetail(),
                Detail.class
        );
        List<String> getBarcodes = details.stream().map(Detail::getBarcode).toList();
        saleDomainRepository.refund(saleId, getBarcodes.toArray(String[]::new));
    }

    @Override
    public SaleDetail getDetailsBySaleId(long saleId) {
        return saleDomainRepository.getDetailsBySaleId(saleId);
    }

    @Override
    public boolean changeStatus(long saleId) {
        Sale getSale = getSaleById(saleId);
        if(getSale == null) {
            return false;
        }
        getSale.setStatus((short) 1);
        saleDomainRepository.saveSale(getSale);
        return true;
    }

    @Override
    public SingleSaleWithDetails getSaleWithDetails(long id) throws JsonProcessingException {
        String json = saleDomainRepository.getSaleWithDetails(id);
        return jsonConverter.deserializeObject(json, SingleSaleWithDetails.class);
    }

    @Override
    public BigDecimal getTotalInDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        if (startDate != null) {
            startDate.minusDays(1);
        } else if (finishDate != null) {
            finishDate.plusDays(1);
        }
        return saleDomainRepository.getTotalInDateRange(startDate, finishDate);
    }

    @Override
    public Long getSoldItemsInDateRange(LocalDateTime startDate, LocalDateTime finishDate) {
        if (startDate != null) {
            //startDate.minusDays(1);
        } else if (finishDate != null) {
           //finishDate.plusDays(1);
        }
        return saleDomainRepository.getSoldItemsInDateRange(startDate, finishDate);
    }
}