package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class BranchOfficeImage {

    private int id;
    private String pathImage;
    private int branchOfficeID;

    private short status;
}
