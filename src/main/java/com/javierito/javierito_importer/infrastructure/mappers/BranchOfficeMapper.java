package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.BranchOffice;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface BranchOfficeMapper {
    BranchOffice toBranchOffice(BranchOfficeEntity target);
    BranchOfficeEntity toBranchOfficeEntity(BranchOffice target);
    void saveChanges(@MappingTarget BranchOfficeEntity target, BranchOfficeEntity source);
}
