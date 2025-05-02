package com.javierito.javierito_importer.domain.models.ItemModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ListItems {
    public Long itemID;
    public String name;
    public String description;
    public String model;
    public BigDecimal price;
    public BigDecimal wholesalePrice;
    public BigDecimal barePrice;
    private BigDecimal purchasePrice;
    public String brand;
    public String category;
    public String subCategory;
    public String dateManufacture;
    public String itemImage;
    public String address;
    public Integer totalStock;
    private LocalDateTime registerDate;
}
