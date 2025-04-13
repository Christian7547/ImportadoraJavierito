package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final ISaleService saleService;

    public SaleController(ISaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping("/SaleReports")
    public ResponseEntity<List<SalesDetails>> getSalesReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to) {

        List<SalesDetails> salesDetails = saleService.getSalesReport(from, to);

        if (salesDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(salesDetails, HttpStatus.OK);
    }
}
