package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.BranchOfficeModels.BranchOffice;
import com.javierito.javierito_importer.infrastructure.dtos.BranchOffice.BranchOfficeDTO;
import com.javierito.javierito_importer.infrastructure.entities.BranchOfficeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BranchOfficeMapper {
    List<BranchOffice> toBranchOffices(List<BranchOfficeEntity> target);
    List<BranchOfficeEntity> toBranchOfficeEntities(List<BranchOffice> target);
    List<BranchOfficeDTO> toBranchOfficesDTO(List<BranchOffice> target);
    BranchOffice toBranchOffice(BranchOfficeEntity target);
    BranchOfficeEntity toBranchOfficeEntity(BranchOffice target);
    void saveChanges(@MappingTarget BranchOfficeEntity target, BranchOfficeEntity source);
}
