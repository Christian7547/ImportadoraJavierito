package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.ItemImage;
import com.javierito.javierito_importer.infrastructure.entities.ItemImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemImageMapper {

    ItemImage toItemImage(ItemImageEntity target);
    //Esta cochinada no sirve, Alguien que lo veo lo arregla por favor
    //ItemImageEntity toItemImageEntity(ItemImage target);
    ItemImageEntity toItemImageEntity(ItemImage target);
    ArrayList<ItemImage> toItemImages(List<ItemImageEntity> target);
    void saveChanges(@MappingTarget ItemImageEntity target, ItemImageEntity source);
}
