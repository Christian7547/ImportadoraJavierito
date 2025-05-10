package com.javierito.javierito_importer.infrastructure.dtos.sale;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SaleDetailDTO {

    @Positive
    private int quantity;

    @NotBlank(message = "The barcode must not be empty or null")
    private String barcode;

    @Positive
    private double subtotal;

    @PositiveOrZero
    private double percentageDiscount;
}
