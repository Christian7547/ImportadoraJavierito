package com.javierito.javierito_importer.application.Utils;

import java.util.Random;

public class Generator {

    private static final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String GenerateRecoveryCode(){
        StringBuilder recoveryCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 5; i++) {
            int index = random.nextInt(characters.length());
            recoveryCode.append(characters.charAt(index));
        }
        return recoveryCode.toString();
    }
}
