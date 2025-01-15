package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ItemImage")
@Data

public class ItemImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "pathItem")
    private String pathItem;

    @Column(name = "itemID")
    private long itemID;
}
