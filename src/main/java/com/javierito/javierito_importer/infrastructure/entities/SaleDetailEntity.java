package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "SaleDetail")
@Data
public class SaleDetailEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "saleID", nullable = false)
    private long saleId;

    @Column(name = "detail", nullable = false)
    private String detail;
}
