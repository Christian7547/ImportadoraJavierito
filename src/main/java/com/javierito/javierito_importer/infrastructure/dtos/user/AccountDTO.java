package com.javierito.javierito_importer.infrastructure.dtos.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDTO {
    private long id;

    @NotBlank(message = "The name must not be empty or null")
    private String name;

    @NotBlank(message = "The lastname must not be empty or null")
    private String lastName;

    private String secondLastName;

    @NotBlank(message = "The ci must not be empty or null")
    private String ci;

    @NotBlank(message = "The phoneNumber must not be empty or null")
    private String phoneNumber;

    @Email(message = "The email is not valid")
    @NotBlank(message = "The email must not be empty or null")
    private String email;
}
