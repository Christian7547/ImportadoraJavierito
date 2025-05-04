package com.javierito.javierito_importer.domain.models.SaleModels;

import com.javierito.javierito_importer.domain.models.SaleDetail;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class Sale {
    private long id;
    private double total;
    private long employeeID;
    private long clientID;
    private BigDecimal percentageDiscount;
    private List<SaleDetail> details;
}
