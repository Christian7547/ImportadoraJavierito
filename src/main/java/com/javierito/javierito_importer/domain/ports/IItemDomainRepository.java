package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Item;

import java.util.ArrayList;

public interface IItemDomainRepository {

    Item createItem(Item item);
    ArrayList<Item> getItems();
    Item getItem(Long itemId);
    Item editItem(Long itemId, Item item);
    void removeItem(Item item);

}
