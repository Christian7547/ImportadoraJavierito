package com.javierito.javierito_importer.infrastructure.dtos.BranchOffice;

import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class ParamsOfficeDTO {
    @Nullable
    private String query;

    @Nullable
    private Integer status;
}
