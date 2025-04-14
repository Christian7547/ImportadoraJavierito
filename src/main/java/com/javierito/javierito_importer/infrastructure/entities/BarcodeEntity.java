package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Barcode")
@Data
public class BarcodeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
}
