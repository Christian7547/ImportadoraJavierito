package com.javierito.javierito_importer.domain.ports;

import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.domain.models.BarcodeModels.BarcodeItem;
import java.util.List;

public interface IBarcodeDomainRepository {

    List<BarcodeItem> getItemBarcodes(long id);
}
