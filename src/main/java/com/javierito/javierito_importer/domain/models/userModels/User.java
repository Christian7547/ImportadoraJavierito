package com.javierito.javierito_importer.domain.models.userModels;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class User {
    private long id;
    private String userName;
    private String password;
    private String role;
    private String email;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
}
