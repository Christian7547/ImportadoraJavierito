package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BranchOfficeEditableDTO {
    private int id;
    private String name;
    private String address;
    private String latitude;
    private String longitude;
    List<OfficeImageEditableDTO> images;
}
