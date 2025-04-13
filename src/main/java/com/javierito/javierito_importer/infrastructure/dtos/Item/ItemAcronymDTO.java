package com.javierito.javierito_importer.infrastructure.dtos.Item;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemAcronymDTO {

    private Long id;
    private String acronym;
}
