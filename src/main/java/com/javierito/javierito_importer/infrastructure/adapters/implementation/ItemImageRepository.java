package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.domain.ports.IItemImageDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IItemImageRepository;
import com.javierito.javierito_importer.infrastructure.mappers.ItemImageMapper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ItemImageRepository implements IItemImageDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private ItemImageMapper itemImageMapper;

    private final IItemImageRepository itemImageRepository;

    public ItemImageRepository(IItemImageRepository itemImageRepository){this.itemImageRepository = itemImageRepository;}

    @Override
    public ItemImage createItemImage(ItemImage itemImage) {
        var toItemImageEntity = itemImageMapper.toItemImageEntity(itemImage);
        toItemImageEntity.setPathItem(itemImage.getPathImage());
        toItemImageEntity.setItemID(itemImage.getItemID());
        var itemImageCreated = itemImageRepository.save(toItemImageEntity);
        return itemImageMapper.toItemImage(itemImageCreated);
    }

    @Override
    public ArrayList<ItemImage> getItemImages() {
        return this.itemImageMapper.toItemImages(this.itemImageRepository.findAll());
    }

    @Override
    public ItemImage getItemImage(Long itemImageId) {
        var itemImageEntity = itemImageRepository.findById(itemImageId);
        return itemImageEntity.map(entity -> itemImageMapper.toItemImage(entity)).orElse(null);
    }

    @Override
    public ItemImage editItemImage(ItemImage itemImage) {
        var itemImageEntity = itemImageRepository.findById(itemImage.getId());
        itemImageMapper.saveChanges(itemImageEntity.get(), itemImageMapper.toItemImageEntity(itemImage));
        var updatedItem = itemImageRepository.save(itemImageEntity.get());
        return itemImageMapper.toItemImage(updatedItem);
    }

    @Override
    public void removeItemImage(ItemImage itemImage) {
        var itemImageEntity = itemImageRepository.findById(itemImage.getId());
        itemImageMapper.saveChanges(itemImageEntity.get(), itemImageMapper.toItemImageEntity(itemImage));
        itemImageRepository.save(itemImageEntity.get());
    }

    @Transactional
    public void createAllItemImages(List<ItemImage> itemImages) {
        for (int i = 0; i < itemImages.size(); i++) {
            entityManager.persist(itemImages.get(i));
            if (i % 50 == 0) {
                entityManager.flush();
                entityManager.clear();
            }
        }
    }
}
