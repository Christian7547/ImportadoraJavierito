package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Client {
    private long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
}
