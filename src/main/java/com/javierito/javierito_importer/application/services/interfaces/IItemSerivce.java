package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemsDTO;
import java.util.List;


public interface IItemSerivce {

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();
    ItemDTO getItemById(Long id);

}
