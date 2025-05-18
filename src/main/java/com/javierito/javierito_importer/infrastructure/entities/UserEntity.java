package com.javierito.javierito_importer.infrastructure.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "User")
@Data
public class UserEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "userName", nullable = false)
    private String userName_;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    private String role;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "status", nullable = false)
    private short status;

    @Column(name = "registerDate", nullable = false)
    private LocalDateTime registerDate;

    @Column(name = "lastUpdate")
    private LocalDateTime lastUpdate;

    @Column(name = "firstLogin", nullable = false)
    private String firstLogin;

    @PrePersist
    private void onCreate(){
        if (this.registerDate == null) {
            this.registerDate = LocalDateTime.now();
        }
        this.status = 1;
        this.firstLogin = "0";
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of((GrantedAuthority) () -> "ROLE_" + role);
    }

    @Override
    public String getUsername() {
        return userName_;
    }
}
