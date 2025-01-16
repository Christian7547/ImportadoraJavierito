package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IStockRepository;
import com.javierito.javierito_importer.infrastructure.mappers.StockMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public class StockRepository implements IStockDomainRepository {

    @Autowired
    private StockMapper stockMapper;

    private final IStockRepository stockRepository;

    public StockRepository(IStockRepository stockRepository) {this.stockRepository = stockRepository;}

    @Override
    public Stock createStock(Stock stock) {
        var toStockEntity = stockMapper.toStockEntity(stock);
        var stockCreated = stockRepository.save(toStockEntity);
        return stockMapper.toStock(stockCreated);
    }

    @Override
    public ArrayList<Stock> getStocks() {
        return this.stockMapper.toStocks(this.stockRepository.findAll());
    }

    @Override
    public Stock getStock(Long stockId){
        var stockEntity = stockRepository.findById(stockId);
        return stockEntity.map(entity -> stockMapper.toStock(entity)).orElse(null);
    }

    @Override
    public Stock editStock(Stock stock) {
        var stockEntity = stockRepository.findById(stock.getId());
        stockMapper.saveChanges(stockEntity.get(), stockMapper.toStockEntity(stock));
        var updatedItem = stockRepository.save(stockEntity.get());
        return stockMapper.toStock(updatedItem);
    }

    @Override
    public void removeStock(Stock stock) {
        var itemEntity = stockRepository.findById(stock.getId());
        stockMapper.saveChanges(itemEntity.get(), stockMapper.toStockEntity(stock));
        stockRepository.save(itemEntity.get());
    }

}
