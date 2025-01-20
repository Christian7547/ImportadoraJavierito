package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Client")
@Data
public class ClientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "secondLastName")
    private String secondLastName;

    @Column(name = "ci", nullable = false)
    private String ci;

    @Column(name = "phoneNumber")
    private String phoneNumber;
}
