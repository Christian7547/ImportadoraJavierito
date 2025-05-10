package com.javierito.javierito_importer.infrastructure.dtos.sale;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Data
public class SaleDTO {

    @Positive
    private double total;

    @Positive
    private long employeeId;

    @Positive
    private long clientId;

    @PositiveOrZero
    private BigDecimal percentageDiscount;

    private List<SaleDetailDTO> details;
}
