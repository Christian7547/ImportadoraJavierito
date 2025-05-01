package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
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
    private String dateManufacture;
    private Short itemAddressID;
    private Long userID;
    private BigDecimal purchasePrice;
    private String acronym;
    private Character itemStatus;
    private String transmission;
    private String cylinderCapacity;
    private Character traction;
    private String itemSeries;
    private String fuel;

    // Item Image
    private String[] pathItems;

    // Stock
    private Integer branchOfficeID;
    private Integer quantity;

    // Barcode
    private String[] barcodes;
}
