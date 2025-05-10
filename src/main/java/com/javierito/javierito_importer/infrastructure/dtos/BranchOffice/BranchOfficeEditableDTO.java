package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class BranchOfficeEditableDTO {
    private int id;

    @NotBlank(message = "The branch name must not be empty or null")
    private String name;

    @NotBlank(message = "The branch address must not be empty or null")
    private String address;

    @NotBlank(message = "The branch latitude must not be empty or null")
    private String latitude;

    @NotBlank(message = "The branch longitude must not be empty or null")
    private String longitude;
    private LocalDateTime registerDate;
    List<OfficeImageEditableDTO> images;
}
