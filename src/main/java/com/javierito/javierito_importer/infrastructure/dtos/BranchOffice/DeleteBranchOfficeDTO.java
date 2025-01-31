package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeleteBranchOfficeDTO {
    private int id;
    private LocalDateTime lastUpdate;
}
