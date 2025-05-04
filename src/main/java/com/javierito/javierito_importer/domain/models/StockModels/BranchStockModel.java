package com.javierito.javierito_importer.domain.models.StockModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BranchStockModel {
    private String branchName;
    private Integer quantity;
}
