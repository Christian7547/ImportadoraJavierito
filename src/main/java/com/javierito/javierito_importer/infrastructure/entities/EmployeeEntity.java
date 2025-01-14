package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Employee")
@Data
public class EmployeeEntity {

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

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "branchOfficeID", nullable = false)
    private int branchOfficeId;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "userID", nullable = false)
    private long userId;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
    }
}
