package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;


import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "Item")
@Data

public class ItemEntity {
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

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "dateManufacture")
    private String dateManufacture;

    @Column(name = "status")
    private Short status;

    @Column(name = "registerDate")
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime  lastUpdate;

    @Column(name = "itemAddressID")
    private Short  itemAddressID;

    @Column(name = "userID")
    private Long userID;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
    }
}
