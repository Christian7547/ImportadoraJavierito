package com.javierito.javierito_importer.infrastructure.dtos.Stock;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class StockBarcode {

    private Long itemId;
    private Long branchOfficeId;
    private int quantity;
    private String acronym;
    private String[] barcodes;

}