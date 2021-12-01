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

    public static boolean isShipSetValid(String set) {

        if (set.length() > 5)
            return false;

        if (set.length() < 4)
            return false;

        if (!set.contains(" "))
            return false;

        if (set.length() == 4) {
            if (!areCoordinatesValid(set.substring(0, 2)))
                return false;

            if (!set.substring(3).matches("[nswe]"))
                return false;
        }

        if (set.length() == 5) {
            if (!areCoordinatesValid(set.substring(0, 3)))
                return false;

            return set.substring(4).matches("[nswe]");
        }

        return true;
    }
}
