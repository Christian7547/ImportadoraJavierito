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
    public long id;

    @Column(name = "name")
    public String name;

    @Column(name = "alias")
    public String alias;

    @Column(name = "description")
    public String description;

    @Column(name = "model")
    public String model;

    @Column(name = "price")
    public BigDecimal price;

    @Column(name = "wholesalePrice")
    public BigDecimal wholesalePrice;

    @Column(name = "barePrice")
    public BigDecimal barePrice;

    @Column(name = "brandID")
    public int brandID;

    @Column(name = "subCategoryID")
    public short subCategoryID;

    @Column(name = "weight")
    public BigDecimal weight;

    @Column(name = "dateManufacture")
    public String dateManufacture;

    @Column(name = "status")
    public short status;

    @Column(name = "registerDate")
    public LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    public LocalDateTime  lastUpdate;

    @Column(name = "itemAddressID")
    public short  itemAddressID;

    @Column(name = "userID")
    public long userID;

    @Column(name = "barcode")
    public String barcode;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
    }
}
