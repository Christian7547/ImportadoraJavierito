package com.javierito.javierito_importer.application.Services.interfaces;

import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.domain.models.BarcodeModels.BarcodeItem;

import java.util.List;

public interface IBarcodeService {

    List<BarcodeItem> getBarcodes(long id);

}
