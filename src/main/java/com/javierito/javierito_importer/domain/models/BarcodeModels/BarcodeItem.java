package com.javierito.javierito_importer.domain.models.BarcodeModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BarcodeItem {
    private Long barcodeId;
    private String barcode;
    private String itemName;
    private String brandName;
    private String itemModel;
}
