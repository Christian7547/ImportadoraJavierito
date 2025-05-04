package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.sql.Timestamp;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO
{
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
    private String dateManufacture;
    private String itemImage;
    private String address;
    private Integer totalStock;
    private Timestamp registerDate;

}

