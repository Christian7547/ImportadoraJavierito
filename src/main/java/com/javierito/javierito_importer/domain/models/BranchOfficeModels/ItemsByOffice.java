package com.javierito.javierito_importer.domain.models.BranchOfficeModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
public class ItemsByOffice {
    private String name;
    private int quantity;
    private Timestamp registerDate;
    private String description;
    private String pathItem;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal purchasePrice;
    private BigDecimal barePrice;
    private String brand;
    private String model;
    private int year;
    private String status;
}
