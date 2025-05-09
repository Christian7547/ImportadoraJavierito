package com.javierito.javierito_importer.domain.models.ItemModels;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ItemUpdate {
    @Column(name = "itemid")
    private Long itemID;

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

    @Column(name = "wholesaleprice")
    private BigDecimal wholesalePrice;

    @Column(name = "bareprice")
    private BigDecimal barePrice;

    @Column(name = "purchaseprice")
    private BigDecimal purchasePrice;

    @Column(name = "brandid")
    private Integer brandID;

    @Column(name = "subcategoryid")
    private Short subCategoryID;

    @Column(name = "datemanufacture")
    private String dateManufacture;

    @Column(name = "itemaddressid")
    private Short itemAddressID;

    @Column(name = "userid")
    private Long userID;

    @Column(name = "acronym")
    private String acronym;

    @Column(name = "itemstatus")
    private Character itemStatus;

    @Column(name = "transmission")
    private String transmission;

    @Column(name = "cylindercapacity")
    private String cylinderCapacity;

    @Column(name = "traction")
    private Character traction;

    @Column(name = "itemseries")
    private String itemSeries;

    @Column(name = "fuel")
    private String fuel;

    @Column(name = "itemimages")
    private String[] itemImages;
}
