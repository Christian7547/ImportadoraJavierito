package com.javierito.javierito_importer.infrastructure.dtos.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResetPasswordDTO {

    @NotBlank(message = "The email must not be empty or null")
    @Email(message = "The email is not valid")
    private String email;

    @NotBlank(message = "The password must not be empty or null")
    private String newPassword;
}
