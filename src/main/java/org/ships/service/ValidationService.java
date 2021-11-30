package org.ships.service;

import java.util.Locale;

public class ValidationService {

    public static boolean areCoordinatesValid(String coordinates) {

        if (coordinates.length() < 2)
            return false;

        if (coordinates.length() > 3)
            return false;

        if (!coordinates.toLowerCase(Locale.ROOT).substring(0, 1).matches("[a-j]"))
            return false;

        boolean isXAxisValid;

        if (coordinates.length() == 3)
            isXAxisValid = coordinates.startsWith("10", 1);
        else
            isXAxisValid = coordinates.substring(1, 2).matches("[1-9]");

        return isXAxisValid;
    }
}
