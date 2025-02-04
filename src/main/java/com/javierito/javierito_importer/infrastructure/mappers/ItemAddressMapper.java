package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.ItemAddress;
import com.javierito.javierito_importer.infrastructure.entities.ItemAddressEntity;
import org.mapstruct.Mapper;
import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemAddressMapper {

    ItemAddress toItemAddress(ItemAddressEntity entity);

    ItemAddressEntity toItemAddressEntity(ItemAddress itemAddress);

    ArrayList<ItemAddress> toItemAdreses(List<ItemAddressEntity> target);

}
