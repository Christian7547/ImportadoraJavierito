package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IBrandService;
import com.javierito.javierito_importer.domain.models.Brand;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/brands")
public class BrandController {

    private final IBrandService brandService;

    public BrandController(IBrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/getAllBrands")
    public ResponseEntity<?> getBradsAsync(){
        ArrayList<Brand> brands = brandService.getAllBrands();
        if(brands != null){
            return new ResponseEntity<>(brands, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get brands", HttpStatus.OK);
    }
}
