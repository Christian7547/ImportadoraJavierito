package com.javierito.javierito_importer.infrastructure.controllers;


import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.ItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/items")
public class ItemController {
    private final IItemSerivce itemSerivce;
    public ItemController(IItemSerivce itemSerivce){this.itemSerivce = itemSerivce;}

    @PostMapping("/createItem")
    public ResponseEntity<?> createItem(@RequestBody ItemDTO request){
        Item item = new Item();
        item.name = request.name;
        item.status = request.status;
        itemSerivce.createItem(item);
        return new ResponseEntity<>(item, HttpStatus.CREATED);
    }
    @PatchMapping("/editItem")
    public ResponseEntity<?> editItem(@PathVariable Long id, @RequestBody ItemDTO request){
        Item item = new Item();
        item.name = request.name;
        item.status = request.status;
        itemSerivce.createItem(item);
        return new ResponseEntity<>(itemSerivce.editItem(id, item), HttpStatus.NO_CONTENT);
    }
    @PatchMapping("/deleteItem")
    public ResponseEntity<?> deleteItem(@RequestBody Item item){
        itemSerivce.removeItem(item);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/getItem")
    public ResponseEntity<Item> getItem(@RequestBody Long id){
        return new ResponseEntity<>(itemSerivce.getItem(id), HttpStatus.OK);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<ArrayList<Item>> getItems(){
        return new ResponseEntity<>(itemSerivce.getItems(), HttpStatus.OK);
    }
}
