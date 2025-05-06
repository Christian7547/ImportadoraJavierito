package com.javierito.javierito_importer.infrastructure.mappers;


import com.javierito.javierito_importer.domain.models.BarcodeModels.Barcode;
import com.javierito.javierito_importer.infrastructure.entities.BarcodeEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BarcodeMapper {

    BarcodeEntity toBarcodeEntity(Barcode target);

    Barcode toBarcode(BarcodeEntity target);

    List<Barcode> toBarcodes(List<BarcodeEntity> target);

    List<BarcodeEntity> toBarcodesEntity(List<Barcode> target);
}
