package com.javierito.javierito_importer.infrastructure.dtos.sale;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDetailDTO {
    private int quantity;
    private String barcode;
    private double subtotal;
}
