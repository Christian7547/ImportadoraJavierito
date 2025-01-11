package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Employee", schema = "public")
@Data
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "lastName", nullable = false)
    private String lastName;

    @Column(name = "secondLastName", nullable = true)
    private String secondLastName;

    @Column(name = "ci", nullable = false)
    private String ci;

    @Column(name = "phoneNumber", nullable = false)
    private String phoneNumber;

    @Column(name = "branchOfficeID", nullable = false)
    private int branchOfficeId;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "registerDate")
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "userID", nullable = false)
    private long userId;

    public EmployeeEntity(String name, String lastName, String secondLastName, String ci, String phoneNumber, int branchOfficeId, long userId) {
        this.name = name;
        this.lastName = lastName;
        this.secondLastName = secondLastName;
        this.ci = ci;
        this.phoneNumber = phoneNumber;
        this.branchOfficeId = branchOfficeId;
        this.userId = userId;
    }
}
