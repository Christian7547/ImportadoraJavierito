package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
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
    private BigDecimal weight;
    private String dateManufacture;
    private Short itemAddressID;
    private Long userID;
    private List<String> itemImages;
}
