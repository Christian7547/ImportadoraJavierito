package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.UpdateItemDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final IItemSerivce itemSerivce;

    public ItemController(IItemSerivce itemSerivce) {this.itemSerivce = itemSerivce;}

    @PostMapping("/insertItem")
    public ResponseEntity<Integer> insertItem(@RequestBody InsertItemDTO item) {

        NewItem newitem = NewItem.builder()
                .name(item.getName())
                .alias(item.getAlias())
                .description(item.getDescription())
                .model(item.getModel())
                .price(item.getPrice())
                .wholesalePrice(item.getWholesalePrice())
                .barePrice(item.getBarePrice())
                .brandID(item.getBrandID())
                .subCategoryID(item.getSubCategoryID())
                .dateManufacture(item.getDateManufacture())
                .itemAddressID(item.getItemAddressID())
                .userID(item.getUserID())
                .acronym(item.getAcronym())
                .pathItems(item.getPathItems())
                .branchOfficeID(item.getBranchOfficeID())
                .quantity(item.getQuantity())
                .barcodes(item.getBarcodes())
                .build();

        var result = itemSerivce.insertItem(newitem);

        if(result != 0)
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(@RequestParam int offset, int limit, String param) {

        var result = itemSerivce.getAllItems(offset, limit, param);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Could not get items", HttpStatus.NO_CONTENT);

    }

    @PostMapping("/getItemByItemID")
    public ResponseEntity<?> getItemByItemID(@RequestBody ItemDTO itemDTO) {

        var result = itemSerivce.getItemById(itemDTO.getItemID());


        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);
    }

    @PatchMapping("/UpdateItem")
    public ResponseEntity<?> updateItem(@RequestBody UpdateItemDTO updateItemDTO) {

        ItemUpdate updated = ItemUpdate.builder()
                .itemID(updateItemDTO.getItemID())
                .name(updateItemDTO.getName())
                .alias(updateItemDTO.getAlias())
                .description(updateItemDTO.getDescription())
                .model(updateItemDTO.getModel())
                .price(updateItemDTO.getPrice())
                .wholesalePrice(updateItemDTO.getWholesalePrice())
                .barePrice(updateItemDTO.getBarePrice())
                .brandID(updateItemDTO.getBrandID())
                .subCategoryID(updateItemDTO.getSubCategoryID())
                .weight(updateItemDTO.getWeight())
                .dateManufacture(updateItemDTO.getDateManufacture())
                .itemAddressID(updateItemDTO.getItemAddressID())
                .userID(updateItemDTO.getUserID())
                .itemImages(updateItemDTO.getItemImages())
                .build();
        var result = itemSerivce.updateItemById(updated);

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeItem (@RequestBody Item item) {

        var result = itemSerivce.deleteItem(item);

        if (result != null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);

    }
}
