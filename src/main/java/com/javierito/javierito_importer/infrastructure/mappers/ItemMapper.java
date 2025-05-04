package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemAcronym;
import com.javierito.javierito_importer.domain.models.ItemModels.ItemUpdate;
import com.javierito.javierito_importer.domain.models.ItemModels.NewItem;
import com.javierito.javierito_importer.infrastructure.dtos.Item.InsertItemDTO;
import com.javierito.javierito_importer.infrastructure.dtos.Item.UpdateItemDTO;
import com.javierito.javierito_importer.infrastructure.entities.ItemEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemEntity target);
    ItemEntity toItemEntity(Item target);
    ArrayList<Item> toItems(List<ItemEntity> target);
    void saveChanges(@MappingTarget ItemEntity target, ItemEntity source);

    @Mapping(target = "id", source = "id")
    @Mapping(target = "acronym", source = "acronym")
    ItemAcronym toItemAcronym(Item target);
    ItemUpdate toItemUpdate(UpdateItemDTO target);
    NewItem toNewItem(InsertItemDTO target);
}
