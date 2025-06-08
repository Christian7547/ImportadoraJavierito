package com.javierito.javierito_importer.application.Services.implementation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javierito.javierito_importer.application.Utils.BarcodeGenerator;
import com.javierito.javierito_importer.application.Services.interfaces.IItemSerivce;
import com.javierito.javierito_importer.application.Utils.JsonConverter;
import com.javierito.javierito_importer.domain.models.Item;
import com.javierito.javierito_importer.domain.models.ItemModels.*;
import com.javierito.javierito_importer.domain.models.StockModels.BranchStockModel;
import com.javierito.javierito_importer.domain.ports.*;
import lombok.RequiredArgsConstructor;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.BeanUtils;
import org.yaml.snakeyaml.util.Tuple;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
public class ItemService implements IItemSerivce {

    private final IItemDomainRepository itemDomainRepository;
    private final JsonConverter jsonConverter;
    private BarcodeGenerator barcodeGenerator = new BarcodeGenerator();



    @Override
    public int insertItem(NewItem NewItem) {

        NewItem newItem = NewItem.builder()
                .name(NewItem.getName())
                .alias(NewItem.getAlias())
                .description(NewItem.getDescription())
                .model(NewItem.getModel())
                .price(NewItem.getPrice())
                .wholesalePrice(NewItem.getWholesalePrice())
                .barePrice(NewItem.getBarePrice())
                .brandID(NewItem.getBrandID())
                .subCategoryID(NewItem.getSubCategoryID())
                .dateManufacture(NewItem.getDateManufacture())
                .itemAddressID(NewItem.getItemAddressID())
                .userID(NewItem.getUserID())
                .acronym(NewItem.getAcronym())
                .purchasePrice(NewItem.getPurchasePrice())
                .pathItems(NewItem.getPathItems())
                .branchOfficeID(NewItem.getBranchOfficeID())
                .quantity(NewItem.getQuantity())
                .barcodes(NewItem.getBarcodes())
                .itemStatus(NewItem.getItemStatus())
                .transmission(NewItem.getTransmission())
                .cylinderCapacity(NewItem.getCylinderCapacity())
                .traction(NewItem.getTraction())
                .itemSeries(NewItem.getItemSeries())
                .fuel(NewItem.getFuel())
                .build();

        String lastBarcode = itemDomainRepository.findLastBarcodeByAcronym(NewItem.getAcronym());
        String[] barcodes = barcodeGenerator.generateBarcode(newItem.getAcronym(), newItem.getQuantity(), lastBarcode);
        newItem.setBarcodes(barcodes);

        return itemDomainRepository.insertItem(newItem);

    }


    @Override
    public Tuple<List<ListItems>, Integer> getAllItems(int limit, int offset, String param, String subCategory, String brand, String itemStatus) {
        var result = itemDomainRepository.getAllItems(limit, offset, param, subCategory, brand, itemStatus);
        Integer totalCount = itemDomainRepository.countAllItems();

        return new Tuple<List<ListItems>, Integer>(result, totalCount);
    }

    @Override
    public ItemWithImages getItemById(Long id) {
        return itemDomainRepository.getItemById(id);
    }

    @Override
    public Allinfo getItemAllInfo(Long id) throws JsonProcessingException {
        ItemAllInfo item = itemDomainRepository.itemAllInfo(id);
        Allinfo data = new Allinfo();

        data.setItemId(item.getItemId());
        data.setName(item.getName());
        data.setAlias(item.getAlias());
        data.setDescription(item.getDescription());
        data.setModel(item.getModel());
        data.setPrice(item.getPrice());
        data.setWholesalePrice(item.getWholesalePrice());
        data.setBarePrice(item.getBarePrice());
        data.setPurchasePrice(item.getPurchasePrice());
        data.setBrandName(item.getBrandName());
        data.setSubCategoryName(item.getSubCategoryName());
        data.setDateManufacture(item.getDateManufacture());
        data.setItemAddressName(item.getItemAddressName());
        data.setAcronym(item.getAcronym());
        data.setItemStatus(item.getItemStatus());
        data.setTransmission(item.getTransmission());
        data.setCylinderCapacity(item.getCylinderCapacity());
        data.setTraction(item.getTraction());
        data.setItemSeries(item.getItemSeries());
        data.setFuel(item.getFuel());
        data.setItemImages(item.getItemImages());
        data.setTotalStock(item.getTotalStock());
        data.setRegisterDate(item.getRegisterDate());

        List<BranchStockModel> branchStocks =
                jsonConverter.deserializeCollection(item.getBranchStocks(), BranchStockModel.class);
        data.setBranchStocks(branchStocks);

        return data;
    }

    @Override
    public BarcodeItemInfo getItemBarcodeInfo(String barcode) {
        return itemDomainRepository.getItemBarcodeInfo(barcode);
    }

    @Override
    public Tuple<List<RecycleBin>, Long> getRecycleBin(int limit, int offset, String param, String subCategory, String brand, String itemStatus) {
        var result = itemDomainRepository.getRecycleBin(limit, offset, param, subCategory, brand, itemStatus);
        Long totalCount = itemDomainRepository.countAllItemsRecycleBin();
        return new Tuple<List<RecycleBin>, Long>(result, totalCount);
    }

    @Override
    public ItemAcronym getItemAcronym(Long id) {
        return itemDomainRepository.getItemAcronym(id);
    }


    @Override
    public ItemUpdate updateItemById(ItemUpdate itemUpdate) {

        ItemUpdate updated = ItemUpdate.builder()
                .itemID(itemUpdate.getItemID())
                .name(itemUpdate.getName())
                .alias(itemUpdate.getAlias())
                .description(itemUpdate.getDescription())
                .model(itemUpdate.getModel())
                .price(itemUpdate.getPrice())
                .wholesalePrice(itemUpdate.getWholesalePrice())
                .barePrice(itemUpdate.getBarePrice())
                .purchasePrice(itemUpdate.getPurchasePrice())
                .brandID(itemUpdate.getBrandID())
                .subCategoryID(itemUpdate.getSubCategoryID())
                .dateManufacture(itemUpdate.getDateManufacture())
                .itemAddressID(itemUpdate.getItemAddressID())
                .userID(itemUpdate.getUserID())
                .acronym(itemUpdate.getAcronym())
                .itemStatus(itemUpdate.getItemStatus())
                .transmission(itemUpdate.getTransmission())
                .cylinderCapacity(itemUpdate.getCylinderCapacity())
                .traction(itemUpdate.getTraction())
                .itemSeries(itemUpdate.getItemSeries())
                .fuel(itemUpdate.getFuel())
                .itemImages(itemUpdate.getItemImages())
                .build();

        return itemDomainRepository.updateItemById(updated);
    }

    @Override
    public void deleteItem(DeleteItem item) {

        itemDomainRepository.deleteItem(item);

    }

    @Override
    public void deleteItemPermanently(DeleteItem item) {

        itemDomainRepository.deleteItemPermanently(item);

    }

    @Override
    public void restoreItem(DeleteItem item) {

        itemDomainRepository.restoreItem(item);

    }

}
