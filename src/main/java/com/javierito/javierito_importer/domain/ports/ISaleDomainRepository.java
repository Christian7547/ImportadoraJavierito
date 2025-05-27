package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleDetail;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleList;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public interface ISaleDomainRepository {
    long createSale (double total, long employeeId, long clientId, BigDecimal percentageDiscount, String details);

    List<SaleList> getAll(Pageable pageable,
                       @Nullable LocalDateTime initDate,
                       @Nullable LocalDateTime finishDate,
                       @Nullable String params);
    Long countAll();
    List<SalesDetails> getSalesReport(LocalDateTime from, LocalDateTime to);

    Sale saveSale(Sale source);

    Sale getSaleById(long id);

    String getSaleWithDetails(long id);

    void deleteDetailBySaleId(long saleId);

    boolean refund(long saleId, String[] barcodes);

    SaleDetail getDetailsBySaleId(long saleId);

    BigDecimal getTotalInDateRange(LocalDateTime startDate, LocalDateTime finishDate);

    Long getSoldItemsInDateRange(LocalDateTime startDate, LocalDateTime finishDate);
}
