package com.javierito.javierito_importer.domain.models.BranchOfficeModels;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.List;

@Getter
@Setter
public class BranchOfficeDetails {
    private int officeId;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private Timestamp registerDate;
    private short status;
    private int ownerId;
    private String owner;
    private List<String> images;


    public BranchOfficeDetails(int officeId, String name, String address, String latitude, String longitude, Timestamp registerDate, short status, int ownerId, String owner) {
        this.officeId = officeId;
        this.name = name;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
        this.registerDate = registerDate;
        this.owner = owner;
        this.ownerId = ownerId;
        this.status = status;
    }
}
