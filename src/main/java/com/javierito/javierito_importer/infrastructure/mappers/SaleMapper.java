package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.domain.models.SaleModels.Detail;
import com.javierito.javierito_importer.domain.models.SaleModels.SaleDetail;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDTO;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDetailDTO;
import com.javierito.javierito_importer.infrastructure.entities.SaleDetailEntity;
import com.javierito.javierito_importer.infrastructure.entities.SaleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface SaleMapper {

    @Mapping(source = "employeeId", target = "employeeID")
    @Mapping(source = "clientId", target = "clientID")
    @Mapping(source = "percentageDiscount", target = "percentageDiscount")
    Sale toSale(SaleDTO target);

    @Mapping(source = "employeeID", target = "employeeId")
    @Mapping(source = "clientID", target = "clientId")
    @Mapping(source = "percentageDiscount", target = "percentageDiscount")
    SaleDTO toSaleDTO(Sale target);

    SaleDetailDTO toDetailDto(Detail target);

    Detail toDetail(SaleDetailDTO target);

    @Mapping(source = "employeeId", target = "employeeID")
    @Mapping(source = "clientId", target = "clientID")
    Sale toSaleFromEntity(SaleEntity target);

    @Mapping(source = "employeeID", target = "employeeId")
    @Mapping(source = "clientID", target = "clientId")
    SaleEntity toEntityFromSale(Sale target);

    SaleDetailEntity toSaleDetailEntity(SaleDetail target);

    SaleDetail toSaleDetail(SaleDetailEntity target);
}
