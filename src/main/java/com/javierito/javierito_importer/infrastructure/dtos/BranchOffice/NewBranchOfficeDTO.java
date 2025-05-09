package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class NewBranchOfficeDTO {
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private int ownerId;
    //images
    private ArrayList<String> pathImages;
}
