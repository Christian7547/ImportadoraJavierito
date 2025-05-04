package com.javierito.javierito_importer.infrastructure.dtos.Item;

import com.javierito.javierito_importer.infrastructure.dtos.Stock.BranchStock;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemAllInfoDTO {
    private Long itemId;
    private String name;
    private String alias;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private BigDecimal purchasePrice;
    private String brandName;
    private String subCategoryName;
    private String dateManufacture;
    private String itemAddressName;
    private String acronym;
    private Character itemStatus;
    private String transmission;
    private String cylinderCapacity;
    private Character traction;
    private String itemSeries;
    private String fuel;
    private List<String> itemImages;
    private Long totalStock;
    private List<BranchStock> branchStocks;
    private Timestamp registerDate;
}
