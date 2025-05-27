package com.javierito.javierito_importer.domain.models.ClientModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ListClients {

    private Long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
    private Long userId;
    private Timestamp registerDate;
    private String employeeName;

}
