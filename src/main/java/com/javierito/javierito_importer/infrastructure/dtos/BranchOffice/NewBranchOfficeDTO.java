package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Data;

import java.util.ArrayList;

@Data
public class NewBranchOfficeDTO {
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    //images
    private ArrayList<String> pathImages;
}
