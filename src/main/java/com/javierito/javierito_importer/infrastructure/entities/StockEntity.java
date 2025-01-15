package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Stock")
@Data

public class StockEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "itemID")
    private long itemID;

    @Column(name = "branchOfficeID")
    private int branchOfficeID;

    @Column(name = "quantity")
    private int quantity;

}
