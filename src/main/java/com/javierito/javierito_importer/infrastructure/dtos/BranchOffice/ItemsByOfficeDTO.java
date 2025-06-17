package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
@AllArgsConstructor
public class ItemsByOfficeDTO {

    @Nullable
    private String param;

    @Nullable
    private String brand;

    @Nullable
    private String status;

    @Positive
    @Min(1)
    private int officeId;
}
