package com.javierito.javierito_importer.domain.models.BranchOfficeModels;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
public class BranchOffice {

    private Integer id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
    private int ownerId;


    public BranchOffice(Integer id, String name, String address, int ownerId) {
        this.name = name;
        this.address = address;
        this.ownerId = ownerId;
        this.id = id;
    }
}
