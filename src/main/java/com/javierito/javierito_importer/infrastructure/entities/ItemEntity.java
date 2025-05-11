package com.javierito.javierito_importer.infrastructure.entities;

import com.javierito.javierito_importer.domain.models.Item;
import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item")
@Data

public class ItemEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "alias")
    private String alias;

    @Column(name = "description")
    private String description;

    @Column(name = "model")
    private String model;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "wholesalePrice")
    private BigDecimal wholesalePrice;

    @Column(name = "barePrice")
    private BigDecimal barePrice;

    @Column(name = "brandID")
    private Integer brandID;

    @Column(name = "subCategoryID")
    private Short subCategoryID;

    @Column(name = "dateManufacture")
    private String dateManufacture;

    @Column(name = "status")
    private Short status;

    @Column(name = "registerDate")
    private Timestamp registerDate;

    @Column(name = "lastUpdate")
    private Timestamp  lastUpdate;

    @Column(name = "itemAddressID")
    private Short  itemAddressID;

    @Column(name = "userID")
    private Long userID;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "purchasePrice")
    private BigDecimal purchasePrice;

    @PrePersist
    private void onCreate() {
        if (this.registerDate == null) {
            this.registerDate = new Timestamp(System.currentTimeMillis());;
        }
        this.status = 1;
    }
}
