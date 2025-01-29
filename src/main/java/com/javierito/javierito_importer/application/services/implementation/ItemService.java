package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.domain.ports.IItemImageDomainRepository;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import com.javierito.javierito_importer.infrastructure.dtos.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.ItemDTO;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<ItemDTO> getAllItems() {
        return itemDomainRepository.getAllItems();
    }

}
