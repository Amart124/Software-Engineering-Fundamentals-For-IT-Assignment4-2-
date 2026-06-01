package com.example.bus;

public class ValidationUtils {

    // D1
    public static boolean validDriverID(String id) {

        if (id == null || id.length() != 10)
            return false;

        if (!Character.isDigit(id.charAt(0)) ||
            !Character.isDigit(id.charAt(1)))
            return false;

        int firstDigit = id.charAt(0) - '0';
        int secondDigit = id.charAt(1) - '0';

        if (firstDigit < 2 || firstDigit > 9)
            return false;

        if (secondDigit < 2 || secondDigit > 9)
            return false;

        int specialCount = 0;

        for (int i = 2; i <= 7; i++) {

            char c = id.charAt(i);

            if (!Character.isLetterOrDigit(c))
                specialCount++;
        }

        if (specialCount < 2)
            return false;

        return Character.isUpperCase(id.charAt(8))
                && Character.isUpperCase(id.charAt(9));
    }

    // D2
    public static boolean validAddress(String address) {

        return address.split("\\|").length == 5;
    }

    // D3
    public static boolean validBirthdate(String birthdate) {

        return birthdate.matches("\\d{2}-\\d{2}-\\d{4}");
    }

    // B1
    public static boolean validBusID(String busID) {

        return busID.matches("\\d{8}");
    }
}