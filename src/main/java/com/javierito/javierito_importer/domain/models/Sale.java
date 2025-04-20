package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class Sale {
    private long id;
    private double total;
    private long employeeID;
    private long clientID;
    private List<SaleDetail> details;
}
