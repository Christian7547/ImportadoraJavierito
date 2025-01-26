package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BranchOfficeDTO {
    private int id;
    private String name;
    private String address;
    private LocalDateTime registerDate;
}
