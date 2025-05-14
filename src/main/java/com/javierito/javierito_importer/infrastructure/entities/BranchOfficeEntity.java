package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "BranchOffice")
@Data
@NoArgsConstructor
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

    @Column(name = "ownerID")
    private int ownerId;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
    }
}
