package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.*;
import java.util.List;

public interface IItemDomainRepository {

    Item deleteItem(Item item);
    Item getItem(Long id);

    int insertItem(NewItem newItem);
    List<ListItems> getAllItems(int limit, int offset, String param, String subCategory, String brand);
    ItemWithImages getItemById(Long itemID);
    ItemUpdate updateItemById(ItemUpdate update);
    String findLastBarcodeByAcronym(String acronym);
    Integer countAllItems();

    ItemAcronym getItemAcronym(Long id);
}
