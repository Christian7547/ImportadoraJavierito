package com.javierito.javierito_importer.domain.models;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Category {

    private long id;
    private String name;
    private short status;
    private LocalDateTime registerDate;
    private LocalDateTime lastUpdate;
}
