package com.javierito.javierito_importer.application.Utils;


import java.util.ArrayList;
import java.util.List;

public class BarcodeGenerator {

    public String[] generateBarcode(String acronym, int quantity) {

        List<String> barcodesList = new ArrayList<>();

        for (int i = 0; i < quantity; i++) {
            String formattedNumber = String.format("%07d", i + 1);
            barcodesList.add(acronym + "-" + formattedNumber);
        }

        return barcodesList.toArray(new String[0]);
    }
}
