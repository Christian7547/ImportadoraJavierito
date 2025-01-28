package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;

public interface IItemDomainRepository {

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);

}
