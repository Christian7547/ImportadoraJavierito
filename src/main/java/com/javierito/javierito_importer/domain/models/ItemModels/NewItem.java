package com.javierito.javierito_importer.domain.models.ItemModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class NewItem {
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
    private String[] pathItems;
    private Integer branchOfficeID;
    private Integer quantity;
    private String[] barcodes;
    private String acronym;
    private BigDecimal purchasePrice;
}
