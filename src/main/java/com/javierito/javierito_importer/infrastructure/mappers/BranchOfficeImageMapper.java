package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchOfficeImageMapper {
    List<BranchOfficeImage> toBranchOfficeImages(List<BranchOfficeImageEntity> target);
    List<BranchOfficeImageEntity> toBranchOfficeImageEntities(List<BranchOfficeImage> target);
    BranchOfficeImage toBranchOfficeImage(BranchOfficeImageEntity target);
    BranchOfficeImageEntity toBranchOfficeImageEntity(BranchOfficeImage target);
}
