package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;


import java.util.List;


public interface IItemSerivce {

    Item deleteItem(DeleteItemDTO itemDto);

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();
    ItemDTO getItemById(Long id);
    UpdateItemDTO updateItemById(UpdateItemDTO updateItemById);

}
