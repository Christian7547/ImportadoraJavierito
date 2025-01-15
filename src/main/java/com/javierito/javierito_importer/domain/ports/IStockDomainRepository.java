package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.Stock;

import java.util.ArrayList;

public interface IStockDomainRepository {

    Stock createStock(Stock stock);
    ArrayList<Stock> getStocks();
    Stock getStock(Long stockId);
    Stock editStock(Stock stock);
    void removeStock(Stock stock);

}
