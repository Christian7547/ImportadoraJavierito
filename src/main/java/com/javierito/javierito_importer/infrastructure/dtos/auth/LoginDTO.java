package com.javierito.javierito_importer.infrastructure.dtos.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginDTO {

    @NotBlank(message = "The username must not be empty or null")
    private String username;

    @NotBlank(message = "The password must not be empty or null")
    private String password;
}
