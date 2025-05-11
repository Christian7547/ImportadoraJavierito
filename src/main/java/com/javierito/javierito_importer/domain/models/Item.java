package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Data
@Builder
public class Item {

    private long id;
    private String name;
    private String alias;
    private String description;
    private String model;
    private BigDecimal price;
    private BigDecimal wholesalePrice;
    private BigDecimal barePrice;
    private int brandID;
    private short subCategoryID;
    private String dateManufacture;
    private short status;
    private Timestamp registerDate;
    private Timestamp  lastUpdate;
    private short  itemAddressID;
    private long userID;
    private String acronym;
    private BigDecimal purchasePrice;

}
