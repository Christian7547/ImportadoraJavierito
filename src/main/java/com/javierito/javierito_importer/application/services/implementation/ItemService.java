package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;

import java.time.LocalDateTime;
import java.util.List;

public class ItemService implements IItemSerivce {

    private final IItemDomainRepository itemDomainRepository;
    private BarcodeGenerator barcodeGenerator = new BarcodeGenerator();

    public ItemService(IItemDomainRepository itemDomainRepository) {

        this.itemDomainRepository = itemDomainRepository;
    }


    @Override
    public int insertItem(InsertItemDTO insertItemDTO) {

        String[] barcodes = barcodeGenerator.generateBarcode(insertItemDTO.getAcronym(), insertItemDTO.getQuantity());
        insertItemDTO.setBarcodes(barcodes);
        return itemDomainRepository.insertItem(insertItemDTO);

    }

    @Override
    public List<ItemsDTO> getAllItems() {
        return itemDomainRepository.getAllItems();
    }

    @Override
    public ItemDTO getItemById(Long id) {
        return itemDomainRepository.getItemById(id);
    }

    @Override
    public UpdateItemDTO updateItemById(UpdateItemDTO updateItemDTO) {
        return itemDomainRepository.updateItemById(updateItemDTO);
    }

    @Override
    public ItemEntity deleteItem(DeleteItemDTO itemDto) {

        ItemEntity item = itemDomainRepository.getItem(itemDto.getItemID());

        if (item == null) {
            return null;
        }

        item.setStatus((short) 0);
        item.setLastUpdate(LocalDateTime.now());
        item.setUserID(itemDto.getUserID());

        return itemDomainRepository.deleteItem(item);
    }

}
