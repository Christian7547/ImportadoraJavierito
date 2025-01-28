package com.javierito.javierito_importer.infrastructure.entities;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ItemAddress")
@Data
public class ItemAddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Short id;

    @Column(name = "name")
    private String name;

}
