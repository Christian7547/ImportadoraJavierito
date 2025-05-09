package com.javierito.javierito_importer.domain.models.ItemModels;

import com.javierito.javierito_importer.domain.models.StockModels.BranchStockModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Allinfo {
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
    private String itemStatus;
    private String transmission;
    private String cylinderCapacity;
    private String traction;
    private String itemSeries;
    private String fuel;
    private List<String> itemImages;
    private Long totalStock;
    private List<BranchStockModel> branchStocks;
    private Timestamp registerDate;
}
