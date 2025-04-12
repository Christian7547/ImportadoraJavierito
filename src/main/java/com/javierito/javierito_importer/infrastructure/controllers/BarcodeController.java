package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.Services.interfaces.IBarcodeService;
import com.javierito.javierito_importer.domain.models.BarcodeModels.BarcodeItem;
import com.javierito.javierito_importer.infrastructure.dtos.Barcode.BarcodeItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeController {

    private final IBarcodeService barcodeService;

    public BarcodeController(IBarcodeService barcodeService) {this.barcodeService = barcodeService;}

    @PostMapping("/getBarcodesbyItem")
    public ResponseEntity<?> getBarcodesbyItem(@RequestBody Long id) {

        List<BarcodeItem> result = barcodeService.getBarcodes(id);

        if (result != null) {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get items", HttpStatus.NO_CONTENT);
    }
}
