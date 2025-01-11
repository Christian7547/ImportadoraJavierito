package com.javierito.javierito_importer.infrastructure.dtos;

import lombok.Data;

@Data
public class UserDTO {
    private String userName;
    private String password;
    private String role;
    private String email;
    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
    private int branchOfficeId;
}
