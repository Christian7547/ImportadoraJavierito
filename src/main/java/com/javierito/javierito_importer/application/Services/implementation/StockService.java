package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IStockService;
import com.javierito.javierito_importer.application.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.domain.models.StockModels.NewStock;
import com.javierito.javierito_importer.domain.ports.IItemDomainRepository;
import com.javierito.javierito_importer.domain.ports.IStockDomainRepository;

public class StockService implements IStockService {

    private final IStockDomainRepository stockDomainRepository;
    private final IItemDomainRepository itemDomainRepository;
    private BarcodeGenerator barcodeGenerator = new BarcodeGenerator();

    public StockService(IStockDomainRepository stockDomainRepository, IItemDomainRepository itemDomainRepository) {

        this.stockDomainRepository = stockDomainRepository;
        this.itemDomainRepository = itemDomainRepository;
    }
    @Override
    public int insertNewStock(NewStock newStock) {

        String lastBarcode = itemDomainRepository.findLastBarcodeByAcronym(newStock.getAcronym());
        String[] barcodes = barcodeGenerator.generateBarcode(newStock.getAcronym(), newStock.getQuantity(), lastBarcode);
        newStock.setBarcodes(barcodes);

        return stockDomainRepository.insertNewStock(newStock);
    }

    @Override
    public long sumActiveItemsQuantity() {
        return stockDomainRepository.sumActiveItemsQuantity();
    }
}
