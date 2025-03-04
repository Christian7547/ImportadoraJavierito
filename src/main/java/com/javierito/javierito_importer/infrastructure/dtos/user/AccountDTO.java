package com.javierito.javierito_importer.infrastructure.dtos.user;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AccountDTO {
    private long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
    private String email;
}
