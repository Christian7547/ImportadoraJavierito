package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "Report")
@Data
public class ReportEntity {

    @Id
    private int id;

    @Column(name = "reportType")
    private String reportType;
}
