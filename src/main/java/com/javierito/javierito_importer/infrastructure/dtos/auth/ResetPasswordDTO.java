package com.javierito.javierito_importer.infrastructure.dtos.auth;

import lombok.Data;

@Data
public class ResetPasswordDTO {
    private String email;
    private String newPassword;
}
