package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.BranchOfficeImage;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.OfficeImageEditableDTO;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeImageEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchOfficeImageMapper {

    @Mapping(source = "pathBranchOffice", target = "pathImage")
    BranchOfficeImage toBranchOfficeImage(BranchOfficeImageEntity target);

    List<BranchOfficeImage> toBranchOfficeImages(List<BranchOfficeImageEntity> target);

    @Mapping(source = "pathImage", target = "pathBranchOffice")
    BranchOfficeImageEntity toBranchOfficeImageEntity(BranchOfficeImage target);

    List<BranchOfficeImageEntity> toBranchOfficeImageEntities(List<BranchOfficeImage> target);

    @Mapping(source = "pathImage", target = "path")
    OfficeImageEditableDTO toOfficeImageEditableDTO(BranchOfficeImage branchOfficeImage);

    List<OfficeImageEditableDTO> toOfficeImageEditableDTOList(List<BranchOfficeImage> target);

    @Mapping(source = "path", target = "pathImage")
    BranchOfficeImage toOfficeImageFromEditableDTO(OfficeImageEditableDTO target);

    List<BranchOfficeImage> toOfficeImageFromEditableDTOList(List<OfficeImageEditableDTO> target);
}
