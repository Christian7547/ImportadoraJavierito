package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class ItemRepository implements IItemDomainRepository {

    @Autowired
    private ItemMapper itemMapper;

    private final IItemRepository itemRepository;

    public ItemRepository(IItemRepository itemRepository){this.itemRepository = itemRepository;}

    @Override
    public Item createItem(Item item) {
        var toItemEntity = itemMapper.toItemEntity(item);
        var itemCreated = itemRepository.save(toItemEntity);
        return itemMapper.toItem(itemCreated);
    }

    @Override
    public ArrayList<Item> getItems() {
        return this.itemMapper.toItems(this.itemRepository.findAll());
    }

    @Override
    public Item getItem(Long itemId){
        var itemEntity = itemRepository.findById(itemId);
        return itemEntity.map(entity -> itemMapper.toItem(entity)).orElse(null);
    }

    @Override
    public Item editItem(Item item) {
        var itemEntity = itemRepository.findById(item.getId());
        itemMapper.saveChanges(itemEntity.get(), itemMapper.toItemEntity(item));
        var updatedItem = itemRepository.save(itemEntity.get());
        return itemMapper.toItem(updatedItem);
    }

    @Override
    public void removeItem(Item item) {
        var itemEntity = itemRepository.findById(item.getId());
        itemMapper.saveChanges(itemEntity.get(), itemMapper.toItemEntity(item));
        itemRepository.save(itemEntity.get());
    }
}
