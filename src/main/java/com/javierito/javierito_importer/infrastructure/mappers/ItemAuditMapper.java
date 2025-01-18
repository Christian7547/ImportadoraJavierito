package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.ItemAudit;
import com.javierito.javierito_importer.infrastructure.entities.ItemAuditEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemAuditMapper {

    ItemAudit toItemAudit(ItemAuditEntity target);
    ItemAuditEntity toItemAuditEntity(ItemAudit target);
    ArrayList<ItemAudit> toItemAudits(List<ItemAuditEntity> target);
    void saveChanges(@MappingTarget ItemAuditEntity target, ItemAuditEntity source);
}
