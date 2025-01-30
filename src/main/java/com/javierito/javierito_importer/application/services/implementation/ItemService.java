package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.ItemsDTO;

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

}
