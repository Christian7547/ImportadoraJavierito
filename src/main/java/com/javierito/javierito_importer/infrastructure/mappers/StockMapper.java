package com.javierito.javierito_importer.infrastructure.mappers;

import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.infrastructure.entities.StockEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public interface StockMapper {

    Stock toStock(StockEntity target);
    StockEntity toStockEntity(Stock target);
    ArrayList<Stock> toStocks(List<StockEntity> target);
    void saveChanges(@MappingTarget StockEntity target, StockEntity source);

}
