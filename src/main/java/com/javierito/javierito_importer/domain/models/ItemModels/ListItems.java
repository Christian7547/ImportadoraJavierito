package com.javierito.javierito_importer.domain.models.ItemModels;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class ListItems {

    private Long itemID;
    private String name;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private BigDecimal purchasePrice;
    private String brand;
    private String category;
    private String subCategory;
    private String alias;
    private String itemImage;
    private String address;
    private Integer totalStock;
    private Timestamp registerDate;
    private String itemStatus;

}
