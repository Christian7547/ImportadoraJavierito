package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IItemAddressService;
import com.javierito.javierito_importer.domain.models.ItemAddress;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/itemAddresses")
public class ItemAddressController {

    private final IItemAddressService itemAddressService;

    public ItemAddressController(IItemAddressService itemAddressService) {
        this.itemAddressService = itemAddressService;
    }

    @GetMapping("/getAllItemAddresses")
    public ResponseEntity<?> getItemAddressAsync(){
        ArrayList<ItemAddress> itemAddresses = itemAddressService.getAllItemAddresses();
        if(itemAddresses != null){
            return new ResponseEntity<>(itemAddresses, HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not get Item Addresses", HttpStatus.OK);
    }

}
