package com.javierito.javierito_importer.domain.models.EmployeeModels;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Employee {
    private long id;
    private String name;
    private String lastName;
    private String secondLastName;
    private String ci;
    private String phoneNumber;
    private int branchOfficeId;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
    private long userId;
}
