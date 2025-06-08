package com.javierito.javierito_importer.application.Services.interfaces;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.*;
import org.yaml.snakeyaml.util.Tuple;


import java.util.List;


public interface IItemSerivce {

    ItemUpdate updateItemById(ItemUpdate itemUpdate);

    void deleteItem(DeleteItem item);
    void deleteItemPermanently(DeleteItem item);
    void restoreItem(DeleteItem item);

    int insertItem(NewItem insertItemDTO);
    Tuple<List<ListItems>, Integer> getAllItems(int limit, int offset, String param, String subCategory, String brand, String itemStatus);
    ItemWithImages getItemById(Long id);

    ItemAcronym getItemAcronym(Long id);

    Allinfo getItemAllInfo(Long id) throws JsonProcessingException;

    BarcodeItemInfo getItemBarcodeInfo(String barcode);

    Tuple<List<RecycleBin>, Long> getRecycleBin(int limit, int offset, String param, String subCategory, String brand, String itemStatus);

}
