package com.javierito.javierito_importer.domain.models.EmployeeModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Manager {
    private int id;
    private String fullName;
}
