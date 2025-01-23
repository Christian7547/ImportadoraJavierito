package com.javierito.javierito_importer.infrastructure.dtos;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class BranchOfficeDTO {
    private int id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    private LocalDateTime registerDate;
    private ArrayList<String> pathImages;
}
