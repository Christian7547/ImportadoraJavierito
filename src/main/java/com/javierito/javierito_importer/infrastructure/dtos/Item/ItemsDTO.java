package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemsDTO
{
    public Long itemID;
    public String name;
    public String description;
    public String model;
    public BigDecimal price;
    public BigDecimal wholesalePrice;
    public BigDecimal barePrice;
    public String brand;
    public String category;
    public String subCategory;
    public String dateManufacture;
    public String itemImage;
    public String address;
    public Integer totalStock;
}

