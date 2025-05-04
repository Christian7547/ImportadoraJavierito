package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleList {

    private long id;

    private Timestamp registerDate;

    private String employee;

    private String client;

    private int itemsQuantity;

    private BigDecimal percentageDiscount;

    private BigDecimal total;
}
