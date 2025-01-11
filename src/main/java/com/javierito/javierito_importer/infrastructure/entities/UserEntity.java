package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "User", schema = "public")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    @Column(name = "email")
    private String email;

    @Column(name = "status")
    private short status;

    @Column(name = "registerDate")
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    public UserEntity(String userName, String password, String role, String email) {
        this.userName = userName;
        this.password = password;
        this.role = role;
        this.email = email;
    }
}
