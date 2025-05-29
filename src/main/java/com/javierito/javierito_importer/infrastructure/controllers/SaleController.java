package com.javierito.javierito_importer.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.SaleModels.*;
import com.javierito.javierito_importer.infrastructure.dtos.sale.DateRangeDTO;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleParamsDTO;
import com.javierito.javierito_importer.infrastructure.exception.types.BadRequestException;
import com.javierito.javierito_importer.infrastructure.exception.types.ResourceNotFoundException;
import org.javatuples.Pair;
import org.springframework.format.annotation.DateTimeFormat;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDTO;
import com.javierito.javierito_importer.infrastructure.mappers.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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
        var saleLists = saleService.getAll(limit, offset, options.getInitDate(), options.getFinishDate(), options.getParams());
        long countTotal = saleService.countAll();
        Pair<List<SaleList>, Long> data = Pair.with(saleLists, countTotal);


        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    @GetMapping("/getSale/{id}")
    public ResponseEntity<?> getSale(@PathVariable long id) throws JsonProcessingException {
        Sale getSale = saleService.getSaleById(id);
        if(getSale == null) {
            throw new ResourceNotFoundException("sale", "id", Long.toString(id));
        }
        SingleSaleWithDetails details = saleService.getSaleWithDetails(id);
        return new ResponseEntity<>(details, HttpStatus.OK);
    }

    @PostMapping("/newSale")
    public ResponseEntity<?> newSale(@RequestBody SaleDTO saleDTO) throws JsonProcessingException {
        Sale toSale = saleMapper.toSale(saleDTO);
        if(saleService.createSale(toSale) <= 0) {
            throw new BadRequestException("could not create the sale");
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
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
            throw new ResourceNotFoundException("reports");
        }
        return new ResponseEntity<>(salesDetails, HttpStatus.OK);
    }

    @PatchMapping("/deleteSale/{id}")
    public ResponseEntity<?> deleteSale(@PathVariable long id, @RequestParam int newStatus){
        boolean success = saleService.deleteSale(id, (short) newStatus);
        if(!success) {
            throw new ResourceNotFoundException("sale", "id", Long.toString(id));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/changeStatus/{id}")
    public ResponseEntity<?> changeStatus (@PathVariable long id) {
        boolean success = saleService.changeStatus(id);
        if(!success) {
            throw new ResourceNotFoundException("sale", "id", Long.toString(id));
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/refund/{saleId}")
    public ResponseEntity<?> refund(@PathVariable long saleId) {
        try {
            saleService.refund(saleId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e){
            throw new BadRequestException(e.getMessage());
        }
    }

    @PostMapping("/getTotalInDateRange")
    public ResponseEntity<?> getTotalInDateRange(@RequestBody DateRangeDTO params) {
        BigDecimal total = saleService.getTotalInDateRange(params.getStartDate(), params.getFinishDate());
        if(total == null) {
            throw new ResourceNotFoundException("Total in range date");
        }
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    @PostMapping("/getSoldItemsInDateRange")
    public ResponseEntity<?> getSoldItemsInDateRange(@RequestBody DateRangeDTO params) {
        Long total = saleService.getSoldItemsInDateRange(params.getStartDate(), params.getFinishDate());
        if(total == null) {
            throw new ResourceNotFoundException("Sold items in range date");
        }
        return new ResponseEntity<>(total, HttpStatus.OK);
    }
}
