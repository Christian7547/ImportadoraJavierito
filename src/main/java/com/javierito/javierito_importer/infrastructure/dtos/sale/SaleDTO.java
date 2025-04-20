package com.javierito.javierito_importer.infrastructure.dtos.sale;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class SaleDTO {
    private double total;
    private long employeeId;
    private long clientId;
    private List<SaleDetailDTO> details;
}
