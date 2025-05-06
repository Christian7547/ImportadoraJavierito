package com.javierito.javierito_importer.domain.models.SaleModels;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SaleDetail {
    private long id;
    private long saleId;
    private String detail;
}
