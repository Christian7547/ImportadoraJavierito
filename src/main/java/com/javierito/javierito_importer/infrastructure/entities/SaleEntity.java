package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Sale")
@Data
public class SaleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

}
