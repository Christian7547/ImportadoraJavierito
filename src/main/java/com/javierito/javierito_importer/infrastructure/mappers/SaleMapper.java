package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.SaleModels.Sale;
import com.javierito.javierito_importer.domain.models.SaleDetail;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDTO;
import com.javierito.javierito_importer.infrastructure.dtos.sale.SaleDetailDTO;
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

    SaleDetailDTO toDetailDto(SaleDetail target);

    SaleDetail toDetail(SaleDetailDTO target);
}
