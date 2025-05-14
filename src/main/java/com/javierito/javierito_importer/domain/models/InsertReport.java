package com.javierito.javierito_importer.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertReport {

    private String name;
    private String pathReport;
    private Long userId;
    private String reportType;
    private Integer affectedRows;

}