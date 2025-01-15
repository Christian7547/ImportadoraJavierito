package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final IItemSerivce itemSerivce;

    public ItemController(IItemSerivce itemSerivce) {this.itemSerivce = itemSerivce;}

    @PostMapping("/insertItem")
    public ResponseEntity<Item> insertItemAsync(@RequestBody InsertItemDTO insertItemDTO){
        Item item = Item.builder()
                .name(insertItemDTO.getName())
                .alias(insertItemDTO.getAlias())
                .description(insertItemDTO.getDescription())
                .model(insertItemDTO.getModel())
                .price(insertItemDTO.getPrice())
                .wholesalePrice(insertItemDTO.getWholesalePrice())
                .barePrice(insertItemDTO.getBarePrice())
                .brandID(insertItemDTO.getBrandID())
                .subCategoryID(insertItemDTO.getSubCategoryID())
                .weight(insertItemDTO.getWeight())
                .dateManufacture(insertItemDTO.getDateManufacture())
                .itemAddressID(insertItemDTO.getItemAddressID())
                .build();

        ItemImage itemImage = ItemImage.builder()
                .pathImage(insertItemDTO.getPathItem())
                .build();

        Stock stock = Stock.builder()
                .branchOfficeID(insertItemDTO.getBranchOfficeID())
                .quantity(insertItemDTO.getQuantity())
                .build();

        var created = itemSerivce.createItem(item, itemImage, stock);

        if(created != null)
            return new ResponseEntity<>(created,HttpStatus.CREATED);
        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/test")
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("OK");
    }
}
