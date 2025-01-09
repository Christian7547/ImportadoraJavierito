package com.javierito.javierito_importer.application.services.implementation;

import com.javierito.javierito_importer.application.services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;

import java.util.ArrayList;

public class ItemService implements IItemSerivce {

    private final IItemDomainRepository itemDomainRepository;

    public ItemService(IItemDomainRepository itemDomainRepository) {this.itemDomainRepository = itemDomainRepository;}

    @Override
    public Item createItem(Item item){return itemDomainRepository.createItem(item);}

    @Override
    public ArrayList<Item> getItems(){return  itemDomainRepository.getItems();}

    @Override
    public Item getItem(Long itemId){return  itemDomainRepository.getItem(itemId);}

    @Override
    public Item editItem(Long itemId, Item item){return  itemDomainRepository.editItem(itemId, item);}

    @Override
    public void removeItem(Item item){
        item.status = 0;
        itemDomainRepository.removeItem(item);
    }

}
