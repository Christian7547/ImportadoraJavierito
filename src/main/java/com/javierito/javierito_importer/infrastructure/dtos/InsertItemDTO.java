package com.javierito.javierito_importer.infrastructure.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertItemDTO {
    // Item
    private String name;
    private String alias;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private Integer brandID;
    private Short subCategoryID;
    private BigDecimal weight;
    private String dateManufacture;
    private Short itemAddressID;
    private Long userID;
    public String barcode;

    // Item Image
    private String pathItem;
    private String pathItem2;
    private String pathItem3;
    private String pathItem4;
    private String pathItem5;


    // Stock
    private Integer branchOfficeID;
    private Integer quantity;
}
