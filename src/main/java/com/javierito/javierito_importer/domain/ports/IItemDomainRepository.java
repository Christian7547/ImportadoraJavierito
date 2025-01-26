package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;

import java.util.ArrayList;

public interface IItemDomainRepository {

    Item createItem(Item item);
    ArrayList<Item> getItems();
    Item getItem(Long itemId);
    Item editItem(Item item);
    void removeItem(Item item);

    // Stored Procedures - Functions
    int insertItem(InsertItemDTO insertItemDTO);

}
