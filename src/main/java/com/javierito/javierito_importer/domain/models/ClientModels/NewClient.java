package com.javierito.javierito_importer.domain.models.ClientModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewClient {

    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
    private Long userId;
}
