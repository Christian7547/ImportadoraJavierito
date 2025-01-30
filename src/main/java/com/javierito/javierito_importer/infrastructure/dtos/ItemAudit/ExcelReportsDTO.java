package com.javierito.javierito_importer.infrastructure.dtos.ItemAudit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ExcelReportsDTO {

    private LocalDateTime startDate;
    private LocalDateTime endDate;

}