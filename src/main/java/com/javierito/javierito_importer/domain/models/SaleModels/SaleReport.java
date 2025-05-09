package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleReport {

    private Long saleId;
    private String employeeFullName;
    private String clientFullName;
    private BigDecimal saleTotal;
    private BigDecimal saleDiscount;
    private Timestamp saleDate;
    private List<Detail> saleDetail;

}
