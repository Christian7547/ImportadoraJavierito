package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Detail {
    private int quantity;
    private String barcode;
    private double subtotal;
    private double percentageDiscount;
}
