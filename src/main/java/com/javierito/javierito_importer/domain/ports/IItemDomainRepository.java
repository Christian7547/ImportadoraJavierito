package com.javierito.javierito_importer.domain.ports;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.*;
import java.util.List;

public interface IItemDomainRepository {

    void deleteItem(DeleteItem item);
    void restoreItem(DeleteItem item);
    void deleteItemPermanently(DeleteItem item);

    Item getItem(Long id);

    int insertItem(NewItem newItem);
    List<ListItems> getAllItems(int limit, int offset, String param, String subCategory, String brand, String itemStatus);
    ItemWithImages getItemById(Long itemID);

    List<RecycleBin> getRecycleBin(int limit, int offset, String param, String subCategory, String brand, String itemStatus);
    Long countAllItemsRecycleBin();

    ItemUpdate updateItemById(ItemUpdate update);
    String findLastBarcodeByAcronym(String acronym);
    Integer countAllItems();

    ItemAcronym getItemAcronym(Long id);

    ItemAllInfo itemAllInfo(Long id) throws JsonProcessingException;

    BarcodeItemInfo getItemBarcodeInfo(String barcode);
}
