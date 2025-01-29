package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.ItemDTO;
import java.util.List;


public interface IItemSerivce {

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemDTO> getAllItems();

}
