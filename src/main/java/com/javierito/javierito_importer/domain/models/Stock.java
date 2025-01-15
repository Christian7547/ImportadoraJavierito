package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Stock {

    private long id;
    private long itemID;
    private int branchOfficeID;
    private int quantity;

}
