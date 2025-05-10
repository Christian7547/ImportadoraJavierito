package com.javierito.javierito_importer.infrastructure.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class UserDTO {

    @NotBlank(message = "The role must not be empty or null")
    private String role;

    @Email(message = "The email is not valid")
    @NotBlank(message = "The email must not be empty or null")
    private String email;

    @NotBlank(message = "The name must not be empty or null")
    private String name;

    @NotBlank(message = "The lastname must not be empty or null")
    private String lastName;

    private String secondLastName;

    @NotBlank(message = "The ci must not be empty or null")
    private String ci;

    @NotBlank(message = "The phoneNumber must not be empty or null")
    private String phoneNumber;

    @Positive
    private int branchOfficeId;
}
