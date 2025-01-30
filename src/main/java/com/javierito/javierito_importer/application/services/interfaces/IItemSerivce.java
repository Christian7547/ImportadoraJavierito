package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;


import java.util.List;


public interface IItemSerivce {

    ItemEntity deleteItem(DeleteItemDTO itemDto);

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();
    ItemDTO getItemById(Long id);
    UpdateItemDTO updateItemById(UpdateItemDTO updateItemById);

}
