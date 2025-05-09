package com.javierito.javierito_importer.infrastructure.dtos.Item;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class UpdateItemDTO {

    private Long itemID;
    private String name;
    private String alias;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private BigDecimal purchasePrice;
    private Integer brandID;
    private Short subCategoryID;
    private String dateManufacture;
    private Short itemAddressID;
    private Long userID;
    private String acronym;
    private Character itemStatus;
    private String transmission;
    private String cylinderCapacity;
    private Character traction;
    private String itemSeries;
    private String fuel;
    private String[] itemImages;
}
