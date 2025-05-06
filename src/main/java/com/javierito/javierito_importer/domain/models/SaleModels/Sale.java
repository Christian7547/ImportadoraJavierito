package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Builder
@Data
public class Sale {
    private long id;
    private double total;
    private long employeeID;
    private long clientID;

    @Nullable
    private LocalDateTime registerDate;

    @Nullable
    private Short status;

    private BigDecimal percentageDiscount;
    private List<Detail> details;
}
