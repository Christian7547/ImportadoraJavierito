package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;

@Data
@Builder
public class NewBranchOfficeDTO {

    @NotBlank(message = "The branch name must not be empty or null")
    private String name;

    @NotBlank(message = "The branch address must not be empty or null")
    private String address;

    @NotBlank(message = "The branch latitude must not be empty or null")
    private String latitude;

    @NotBlank(message = "The branch longitude must not be empty or null")
    private String longitude;

    @PositiveOrZero
    private short status;

    @Positive
    private int ownerId;

    private ArrayList<String> pathImages;
}
