package com.javierito.javierito_importer.domain.models.ItemModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BarcodeItemInfo {
    private String barcode;
    private String itemName;
    private Long itemId;
    private BigDecimal purchasePrice;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private String branchName;
    private Timestamp barcodeRegisterDate;
}
