package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemWithImages;
import com.javierito.javierito_importer.domain.models.ItemModels.ListItems;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import org.yaml.snakeyaml.util.Tuple;


import java.util.List;


public interface IItemSerivce {

    ItemUpdate updateItemById(ItemUpdate itemUpdate);
    Item deleteItem(Item item);

    int insertItem(NewItem insertItemDTO);
    Tuple<List<ListItems>, Integer> getAllItems(int offset, int limit, String param);
    ItemWithImages getItemById(Long id);
}
