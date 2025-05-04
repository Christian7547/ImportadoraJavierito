package com.javierito.javierito_importer.domain.models.BranchOfficeModels;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BranchOffice {

    private int id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
}
