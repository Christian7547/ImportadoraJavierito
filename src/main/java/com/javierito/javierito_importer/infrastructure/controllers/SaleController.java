package com.javierito.javierito_importer.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Services.interfaces.ISaleService;
import com.javierito.javierito_importer.domain.models.Sale;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDTO;
import com.javierito.javierito_importer.infrastructure.mappers.SaleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sale")
@RequiredArgsConstructor
public class SaleController {

    private final SaleMapper saleMapper;
    private final ISaleService saleService;

    @PostMapping("/newSale")
    public ResponseEntity<?> newSale(@RequestBody SaleDTO saleDTO) throws JsonProcessingException {
        Sale toSale = saleMapper.toSale(saleDTO);
        if(saleService.createSale(toSale) > 0)
            return new ResponseEntity<>(HttpStatus.CREATED);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
