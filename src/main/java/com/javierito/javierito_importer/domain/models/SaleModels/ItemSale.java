package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@NoArgsConstructor
public class ItemSale {
    private String name;
    private String image;
    private double price;
    private String barcode;
    private double percentageDiscount;
    private int quantity;
    private double subtotal;
}
