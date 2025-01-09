package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Item", schema = "public")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "name")
    public String name;

    @Column(name = "status")
    public short status;

}
