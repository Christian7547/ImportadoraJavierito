package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.StockModels.NewStock;

public interface IStockService {

    int insertNewStock(NewStock newStock);

    long sumActiveItemsQuantity();
}
