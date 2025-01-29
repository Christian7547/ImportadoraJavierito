package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemsDTO;
import java.util.List;

public interface IItemDomainRepository {

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);
    List<ItemsDTO> getAllItems();

}
