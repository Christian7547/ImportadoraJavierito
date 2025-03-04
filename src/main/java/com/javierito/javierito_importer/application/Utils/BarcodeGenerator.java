package com.javierito.javierito_importer.application.Utils;


import java.util.ArrayList;
import java.util.List;

public class BarcodeGenerator {

    public String[] generateBarcode(String acronym, int quantity, String lastBarcode) {

        List<String> barcodesList = new ArrayList<>();
        int lastNumber = 0;

        if (lastBarcode != null && lastBarcode.contains("-")) {
            String[] parts = lastBarcode.split("-");
            lastNumber = Integer.parseInt(parts[1]);
        }

        for (int i = 0; i < quantity; i++) {
            String formattedNumber = String.format("%07d", lastNumber + i + 1);
            barcodesList.add(acronym + "-" + formattedNumber);
        }

        return barcodesList.toArray(new String[0]);
    }
}
