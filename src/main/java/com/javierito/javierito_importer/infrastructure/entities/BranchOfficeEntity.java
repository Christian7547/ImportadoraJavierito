package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "BranchOffice")
@Data
public class BranchOfficeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "latitude", nullable = false)
    private String latitude;

    @Column(name = "longitude", nullable = false)
    private String longitude;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
    }

    public BranchOfficeEntity(int id, String name, String address, String latitude, String longitude) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
