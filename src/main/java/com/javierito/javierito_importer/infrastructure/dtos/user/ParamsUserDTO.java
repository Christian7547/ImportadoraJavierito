package com.javierito.javierito_importer.infrastructure.dtos.user;


import lombok.Getter;
import lombok.Setter;
import org.springframework.lang.Nullable;

@Getter
@Setter
public class ParamsUserDTO {

    @Nullable
    private Short status;

    @Nullable
    private String role;

    @Nullable
    private Integer officeId;

    @Nullable
    private String someName;
}
