package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;


import java.util.List;

public interface IItemDomainRepository {

    Item deleteItem(Item item);
    Item getItem(Long id);

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();
    ItemDTO getItemById(Long itemID);
    UpdateItemDTO updateItemById(UpdateItemDTO updateItemDTO);

}
