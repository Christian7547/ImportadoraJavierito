package com.javierito.javierito_importer.infrastructure.adapters.implementation;


import com.javierito.javierito_importer.domain.models.Stock;
import com.javierito.javierito_importer.domain.models.StockModels.NewStock;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IStockRepository;
import com.javierito.javierito_importer.infrastructure.mappers.StockMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;


@Repository
public class StockRepository implements IStockDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

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

    @Override
    public int insertNewStock(NewStock newStock) {
        StoredProcedureQuery procedureQuery = entityManager.createStoredProcedureQuery("usp_stock_barcodes");

        procedureQuery.registerStoredProcedureParameter("p_item_id", Long.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_branch_office_id", Long.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_quantity", Integer.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_barcodes", String[].class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_user_id", Long.class, ParameterMode.IN);
        procedureQuery.registerStoredProcedureParameter("p_rows_affected", Integer.class, ParameterMode.OUT);

        procedureQuery.setParameter("p_item_id", newStock.getItemId());
        procedureQuery.setParameter("p_branch_office_id", newStock.getBranchOfficeId());
        procedureQuery.setParameter("p_quantity", newStock.getQuantity());
        procedureQuery.setParameter("p_barcodes", newStock.getBarcodes());
        procedureQuery.setParameter("p_user_id", newStock.getUserId());

        procedureQuery.execute();

        return (int) procedureQuery.getOutputParameterValue("p_rows_affected");
    }

    @Override
    public long sumActiveItemsQuantity() {
        String sql = "SELECT * FROM ufc_sum_active_items_quantity()";
        Query query = entityManager.createNativeQuery(sql, Long.class);
        return (Long) query.getSingleResult();
    }
}
