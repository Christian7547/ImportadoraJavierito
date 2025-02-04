package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.Services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.dtos.Item.*;

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
    public List<ItemsDTO> getAllItems(int offset, int limit, String param) {
        return itemDomainRepository.getAllItems(offset, limit, param);
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
