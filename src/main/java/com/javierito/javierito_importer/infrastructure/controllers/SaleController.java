package com.javierito.javierito_importer.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleReport;
import com.javierito.javierito_importer.domain.models.SaleModels.SalesDetails;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleParamsDTO;
import org.springframework.format.annotation.DateTimeFormat;
import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDTO;
import com.javierito.javierito_importer.infrastructure.mappers.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleMapper saleMapper;
    private final ISaleService saleService;

    @PostMapping("/getSales")
    public ResponseEntity<?> getSales(@RequestParam(defaultValue = "5")int limit,
                                      @RequestParam(defaultValue = "1")int offset,
                                      @RequestBody SaleParamsDTO options){
        var data = saleService.getAll(limit, offset, options.getInitDate(), options.getFinishDate(), options.getParams());
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @PostMapping("/newSale")
    public ResponseEntity<?> newSale(@RequestBody SaleDTO saleDTO) throws JsonProcessingException {
        Sale toSale = saleMapper.toSale(saleDTO);
        if(saleService.createSale(toSale) > 0)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/SaleReports")
    public ResponseEntity<List<SaleReport>> getSalesReport(
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime from,

            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
            LocalDateTime to) throws JsonProcessingException {

        List<SaleReport> salesDetails = saleService.getSalesReport(from, to);

        if (salesDetails.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(salesDetails, HttpStatus.OK);
    }

    @PatchMapping("/deleteSale/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable long id, @RequestParam int newStatus){
        boolean success = saleService.deleteSale(id, (short) newStatus);
        if(success)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/refund/{saleId}")
    public ResponseEntity<?> refund(@PathVariable long saleId) {
        try {
            saleService.refund(saleId);
            return new ResponseEntity<>("Successful refund", HttpStatus.OK);
        } catch (Exception e){
            throw new RuntimeException(e.getMessage());
        }
    }
}
