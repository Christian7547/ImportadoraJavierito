package com.javierito.javierito_importer.domain.models;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Brand {

    private Integer id;
    private String name;
}
