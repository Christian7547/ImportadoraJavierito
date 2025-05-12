package com.javierito.javierito_importer.domain.models.StockModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NewStock {

    private Long itemId;
    private Long branchOfficeId;
    private int quantity;
    private String acronym;
    private String[] barcodes;
    private Long userId;

}
