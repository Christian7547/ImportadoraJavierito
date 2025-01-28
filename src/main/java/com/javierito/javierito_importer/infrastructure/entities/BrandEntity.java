package com.javierito.javierito_importer.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Brand")
@Data
public class BrandEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String name;
}
