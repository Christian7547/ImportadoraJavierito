package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.domain.ports.IItemImageDomainRepository;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import org.springframework.transaction.annotation.Transactional;

public class ItemService implements IItemSerivce {

    private final IItemDomainRepository itemDomainRepository;
    private final IItemImageDomainRepository ItemImageDomainRepository;
    private final IStockDomainRepository stockDomainRepository;

    public ItemService(IItemDomainRepository itemDomainRepository,
                       IItemImageDomainRepository ItemImageDomainRepository,
                       IStockDomainRepository stockDomainRepository) {

        this.itemDomainRepository = itemDomainRepository;
        this.ItemImageDomainRepository = ItemImageDomainRepository;
        this.stockDomainRepository = stockDomainRepository;
    }

    @Transactional
    @Override
    public Item createItem(Item item, ItemImage itemImage, Stock stock) {

        var itemCreated = itemDomainRepository.createItem(item);
        long itemId = itemCreated.getId();

        itemImage.setItemID(itemId);
        var itemImageCrated = ItemImageDomainRepository.createItemImage(itemImage);

        stock.setItemID(itemId);
        var stockCreated = stockDomainRepository.createStock(stock);

        return itemCreated;
    }

}
