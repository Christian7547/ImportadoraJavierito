package com.javierito.javierito_importer.domain.models.BranchOfficeModels;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class OfficeList {
    private int id;
    private String name;
    private String address;
    private Timestamp registerDate;
    private short status;
    private String owner;
}
