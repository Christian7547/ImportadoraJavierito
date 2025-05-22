package com.javierito.javierito_importer.domain.models.BarcodeModels.exception;

public class ReportNotFoundException extends RuntimeException {
    public ReportNotFoundException(String message) {
        super(message);
    }
}
