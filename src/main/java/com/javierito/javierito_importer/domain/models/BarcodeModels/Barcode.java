package com.javierito.javierito_importer.domain.models.BarcodeModels;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Barcode {
    private long id;
    private String barcode;
    private short status;
    private long stockId;
    private LocalDateTime registerDate;
}
