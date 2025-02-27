package com.javierito.javierito_importer.application.Utils;

import java.util.Random;

public class Generator {

    private static String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generateRecoveryCode(){
        StringBuilder recoveryCode = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 7; i++) {
            int index = random.nextInt(characters.length());
            recoveryCode.append(characters.charAt(index));
        }
        return recoveryCode.toString();
    }

    public static String generateUsername(String name, String lastName, String secondLastName){
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int number = random.nextInt(10000, 100000);
        String nameSubstring = name.substring(0, 1);
        String lastNameSubstring = lastName.substring(0, 1);
        String sLastNameSubstring = "";
        if(!secondLastName.isBlank()){
            sLastNameSubstring = secondLastName.substring(0, 1);
        }
        builder.append(nameSubstring);
        builder.append(lastNameSubstring);
        builder.append(sLastNameSubstring);
        builder.append(number);
        return builder.toString();
    }

    public static String generatePassword(){
        String specialCharacters = "$-_!/*+.@%#";
        String allCharacters = characters + specialCharacters;
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        int length = random.nextInt(8, 13);
        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allCharacters.length());
            password.append(allCharacters.charAt(randomIndex));
        }
        return password.toString();
    }
}
