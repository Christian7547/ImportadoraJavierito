package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;


public interface IItemSerivce {

    //Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);

}
