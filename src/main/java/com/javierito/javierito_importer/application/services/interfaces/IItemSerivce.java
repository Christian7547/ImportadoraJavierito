package com.javierito.javierito_importer.application.services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.domain.models.Stock;

import java.util.List;

public interface IItemSerivce {

    Item createItem(Item item, List<ItemImage> itemImages, Stock stock);

}
