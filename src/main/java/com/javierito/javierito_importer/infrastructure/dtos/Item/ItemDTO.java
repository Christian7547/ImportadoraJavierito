package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemDTO {
    private Long itemID;
    private String name;
    private String alias;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private Integer brandID;
    private Short subCategoryID;
    private String dateManufacture;
    private Short itemAddressID;
    private Long userID;
    private String acronym;
    private BigDecimal purchasePrice;
    private Character itemStatus;
    private String transmission;
    private String cylinderCapacity;
    private Character traction;
    private String itemSeries;
    private String fuel;
    private String[] itemImages;
}