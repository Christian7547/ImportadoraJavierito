package com.javierito.javierito_importer.infrastructure.mappers;


import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    Item toItem(ItemEntity itemEntity);
    ItemEntity toItemEntity(Item item);
    ArrayList<Item> toItems(List<ItemEntity> itemEntities);
    void saveChanges(@MappingTarget ItemEntity target, ItemEntity source);
}
