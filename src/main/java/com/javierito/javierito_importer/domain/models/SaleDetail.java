package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDetail {
    private int quantity;
    private String barcode;
    private double subtotal;
}
