package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;


import java.util.List;


public interface IItemSerivce {

    Item deleteItem(Item item);

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems(int offset, int limit, String param);
    ItemDTO getItemById(Long id);
    UpdateItemDTO updateItemById(UpdateItemDTO updateItemById);

}
