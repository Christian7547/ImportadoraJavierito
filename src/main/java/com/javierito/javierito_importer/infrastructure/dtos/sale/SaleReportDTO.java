package com.javierito.javierito_importer.infrastructure.dtos.sale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaleReportDTO {

    private Long saleId;
    private String employeeFullName;
    private String clientFullName;
    private BigDecimal saleTotal;
    private BigDecimal saleCommission;
    private BigDecimal saleDiscount;
    private LocalDateTime saleDate;
    private Map<String, Object> saleDetail;

}
