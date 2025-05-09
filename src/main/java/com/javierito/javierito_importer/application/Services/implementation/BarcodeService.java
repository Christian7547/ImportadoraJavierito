package com.javierito.javierito_importer.application.Services.implementation;

import com.javierito.javierito_importer.application.Services.interfaces.IBarcodeService;
import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.domain.models.BarcodeModels.BarcodeItem;
import com.javierito.javierito_importer.domain.ports.IBarcodeDomainRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


public class BarcodeService implements IBarcodeService {

    private final IBarcodeDomainRepository barcodeDomainRepository;

    public BarcodeService(IBarcodeDomainRepository barcodeDomainRepository) {
        this.barcodeDomainRepository = barcodeDomainRepository;
    }

    @Override
    public List<BarcodeItem> getBarcodes(long id) {
        return barcodeDomainRepository.getItemBarcodes(id);
    }
}
