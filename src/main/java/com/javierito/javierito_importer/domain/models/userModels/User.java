package com.javierito.javierito_importer.domain.models.userModels;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private long id;
    private String userName;
    @JsonIgnore
    private String password;
    private String role;
    private String email;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
    private String firstLogin;
}
