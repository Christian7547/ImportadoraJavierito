package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Barcode")
@Data
public class BarcodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "barcode", nullable = false)
    private String barcode;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "stockID", nullable = false)
    private long stockId;

    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;
}
