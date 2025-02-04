package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.infrastructure.entities.ItemImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemImageMapper {

    @Mapping(source = "pathItem", target = "pathImage")
    ItemImage toItemImage(ItemImageEntity target);
    @Mapping(source = "pathImage", target = "pathItem")
    ItemImageEntity toItemImageEntity(ItemImage target);
    ArrayList<ItemImage> toItemImages(List<ItemImageEntity> entities);
    void saveChanges(@MappingTarget ItemImageEntity target, ItemImageEntity source);
}
