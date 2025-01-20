package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchOfficeImageMapper {
    BranchOfficeImage toBranchOfficeImage(BranchOfficeImageEntity target);
    BranchOfficeImageEntity toBranchOfficeImageEntity(BranchOfficeImage target);
}
