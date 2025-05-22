package com.javierito.javierito_importer.infrastructure.adapters.implementation;

import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.domain.models.BarcodeModels.BarcodeItem;
import com.javierito.javierito_importer.domain.ports.IBarcodeDomainRepository;
import com.javierito.javierito_importer.infrastructure.adapters.interfaces.IBarcodeRepository;
import com.javierito.javierito_importer.infrastructure.entities.BarcodeEntity;
import com.javierito.javierito_importer.infrastructure.mappers.BarcodeMapper;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class BarcodeRepository implements IBarcodeDomainRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private BarcodeMapper barcodeMapper;

    private final IBarcodeRepository barcodeRepository;

    public BarcodeRepository(IBarcodeRepository barcodeRepository) {
        this.barcodeRepository = barcodeRepository;
    }


    @Override
    public List<BarcodeItem> getItemBarcodes(long id) {
        String sql = "SELECT * FROM ufc_get_barcodes_by_item(?)";
        List<Object[]> results = entityManager.createNativeQuery(sql)
                .setParameter(1, id)
                .getResultList();

        List<BarcodeItem> barcodeItems = new ArrayList<>();

        for (Object[] row : results) {
            BarcodeItem barcodeItem = new BarcodeItem();
            barcodeItem.setBarcodeId(((Number) row[0]).longValue());
            barcodeItem.setBarcode((String) row[1]);
            barcodeItem.setItemName((String) row[2]);
            barcodeItem.setBrandName((String) row[3]);
            barcodeItem.setItemModel((String) row[4]);
            barcodeItem.setItemAlias((String) row[5]);
            barcodeItem.setRegisterDate((Timestamp) row[6]);
            barcodeItems.add(barcodeItem);
        }
        return barcodeItems;
    }
}
