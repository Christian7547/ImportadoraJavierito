package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final IItemSerivce itemSerivce;

    public ItemController(IItemSerivce itemSerivce) {this.itemSerivce = itemSerivce;}

    @PostMapping("/insertItem")
    public ResponseEntity<Integer> insertItem(@RequestBody InsertItemDTO item) {
        
        var result = itemSerivce.insertItem(item);

        if(result != 0)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems() {

        var result = itemSerivce.getAllItems();

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Could not get items", HttpStatus.OK);

    }
}
