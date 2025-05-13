package com.javierito.javierito_importer.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Report {

    private Long reportId;
    private String name;
    private Timestamp registerDate;
    private String pathReport;
    private Long userId;
    private String reportType;
    private String employeeName;

}
