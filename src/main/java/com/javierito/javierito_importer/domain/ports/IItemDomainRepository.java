package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemsDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.UpdateItemDTO;

import java.util.List;

public interface IItemDomainRepository {

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();
    ItemDTO getItemById(Long itemID);
    UpdateItemDTO updateItemById(UpdateItemDTO updateItemDTO);

}
