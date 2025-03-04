package com.javierito.javierito_importer.domain.models.userModels;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserList {
    private long userId;
    private String username;
    private String fullname;
    private String ci;
    private String phone;
    private String email;
    private String role;
    private short status;
    private String office;
}
