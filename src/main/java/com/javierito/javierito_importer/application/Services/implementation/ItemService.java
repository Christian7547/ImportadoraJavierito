package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.Services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemWithImages;
import com.javierito.javierito_importer.domain.models.ItemModels.ListItems;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.domain.ports.*;
import org.yaml.snakeyaml.util.Tuple;

import java.time.LocalDateTime;
import java.util.List;

public class ItemService implements IItemSerivce {

    private final IItemDomainRepository itemDomainRepository;
    private BarcodeGenerator barcodeGenerator = new BarcodeGenerator();

    public ItemService(IItemDomainRepository itemDomainRepository) {

        this.itemDomainRepository = itemDomainRepository;
    }


    @Override
    public int insertItem(NewItem NewItem) {

        String lastBarcode = itemDomainRepository.findLastBarcodeByAcronym(NewItem.getAcronym());
        String[] barcodes = barcodeGenerator.generateBarcode(NewItem.getAcronym(), NewItem.getQuantity(), lastBarcode);
        NewItem.setBarcodes(barcodes);
        return itemDomainRepository.insertItem(NewItem);

    }

    @Override
    public Tuple<List<ListItems>, Integer> getAllItems(int offset, int limit, String param) {
        var result = itemDomainRepository.getAllItems(offset, limit, param);
        Integer totalCount = itemDomainRepository.countAllItems();

        return new Tuple<List<ListItems>, Integer>(result, totalCount);
    }

    @Override
    public ItemWithImages getItemById(Long id) {
        return itemDomainRepository.getItemById(id);
    }

    @Override
    public ItemUpdate updateItemById(ItemUpdate itemUpdate) {
        return itemDomainRepository.updateItemById(itemUpdate);
    }

    @Override
    public Item deleteItem(Item itemDto) {

        Item item = itemDomainRepository.getItem(itemDto.getId());
        if (item == null) {
            return null;
        }

        item.setStatus((short) 0);
        item.setLastUpdate(LocalDateTime.now());
        item.setUserID(itemDto.getUserID());

        return itemDomainRepository.deleteItem(item);
    }


}
