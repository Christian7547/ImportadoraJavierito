package com.javierito.javierito_importer.infrastructure.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "BranchOfficeImage")
@Data
public class BranchOfficeImageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "pathBranchOffice")
    private String pathBranchOffice;

    @Column(name = "branchOfficeID")
    private int branchOfficeID;

    public BranchOfficeImageEntity(int id, String pathBranchOffice) {
        this.id = id;
        this.pathBranchOffice = pathBranchOffice;
    }
}
