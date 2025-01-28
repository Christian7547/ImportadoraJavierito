package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class SubCategory {

    private Integer id;
    private String name;
    private Short cateoryID;
    private Short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;

}
