package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class SingleSaleWithDetails {
    private long saleId;
    private double total;
    private String client;
    private short status;
    private String employee;
    private Timestamp registerDate;
    private int itemsQuantity;
    private List<ItemSale> details;
}
