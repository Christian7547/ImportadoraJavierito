package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IStockService;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.domain.models.StockModels.NewStock;
import com.javierito.javierito_importer.infrastructure.dtos.Stock.StockBarcode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    private final IStockService stockService;

    public StockController(IStockService stockService) {this.stockService = stockService;}

    @PostMapping("/insertStock")
    public ResponseEntity<Integer> insertItem(@RequestBody StockBarcode barcode) {

        NewStock stock = NewStock.builder()
                .itemId(barcode.getItemId())
                .quantity(barcode.getQuantity())
                .branchOfficeId(barcode.getBranchOfficeId())
                .acronym(barcode.getAcronym())
                .barcodes(barcode.getBarcodes())
                .build();

        var result = stockService.insertNewStock(stock);

        if(result != 0)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/sumQuantity")
    public ResponseEntity<?> getTotalItems() {
        return new ResponseEntity<>(stockService.sumActiveItemsQuantity(), HttpStatus.OK);
    }
}
