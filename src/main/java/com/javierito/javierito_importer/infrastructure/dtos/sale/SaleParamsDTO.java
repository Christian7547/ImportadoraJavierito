package com.javierito.javierito_importer.infrastructure.dtos.sale;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.lang.Nullable;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class SaleParamsDTO {

    @Nullable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime initDate;

    @Nullable
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private LocalDateTime finishDate;

    @Nullable
    private String params;
}
