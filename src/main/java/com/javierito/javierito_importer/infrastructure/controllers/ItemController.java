package com.javierito.javierito_importer.infrastructure.controllers;

import com.javierito.javierito_importer.application.Services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemAcronymDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.UpdateItemDTO;
import com.javierito.javierito_importer.infrastructure.mappers.ItemMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

    private final ItemMapper itemMapper;
    private final IItemSerivce itemSerivce;

    @PostMapping("/insertItem")
    public ResponseEntity<Integer> insertItem(@RequestBody InsertItemDTO item) {

        NewItem toNewItem = itemMapper.toNewItem(item);
        int result = itemSerivce.insertItem(toNewItem);

        if (result != 0) {
            return new ResponseEntity<>(result, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllItems")
    public ResponseEntity<?> getAllItems(@RequestParam(defaultValue = "5") int limit,
                                         @RequestParam(defaultValue = "1") int offset,
                                         String param,
                                         String subCategory,
                                         String brand){

        var result = itemSerivce.getAllItems(limit, offset, param, subCategory, brand);

        if (result != null)
            return new ResponseEntity<>(new Object(){ public final Object data = result._1(); public final int total = result._2();}, HttpStatus.OK);
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

        ItemUpdate toItemUpdate = itemMapper.toItemUpdate(updateItemDTO);
        var updated = itemSerivce.updateItemById(toItemUpdate);

        if (updated == null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/removeItem")
    public ResponseEntity<?> removeItem (@RequestBody Item item) {

        var result = itemSerivce.deleteItem(item);

        if (result != null)
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<>("Item not found", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/itemAcronym")
    public ResponseEntity<?> itemAcronym(@RequestBody ItemAcronymDTO itemDTO) {

        var result = itemSerivce.getItemAcronym(itemDTO.getId());

        if (result != null)
            return new ResponseEntity<>(result, HttpStatus.OK);
        return new ResponseEntity<>("Could not get item", HttpStatus.NOT_FOUND);
    }
}
