package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.ItemDTO;
import java.util.List;

public interface IItemDomainRepository {

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemDTO> getAllItems();

}
