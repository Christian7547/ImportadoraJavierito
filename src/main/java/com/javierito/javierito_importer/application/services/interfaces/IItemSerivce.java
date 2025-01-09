package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;

import java.util.ArrayList;

public interface IItemSerivce {
    Item createItem(Item item);
    ArrayList<Item> getItems();
    Item getItem(Long itemId);
    Item editItem(Long itemId, Item item);
    void removeItem(Item item);
}
