package org.ships.service;

import java.util.Locale;

public class CoordinatesService {

    public static int getXFromCoordinates(String coordinates) {

        char letter = coordinates.toLowerCase(Locale.ROOT).charAt(0);

        return switch (letter) {
            case 'a' -> 0;
            case 'b' -> 1;
            case 'c' -> 2;
            case 'd' -> 3;
            case 'e' -> 4;
            case 'f' -> 5;
            case 'g' -> 6;
            case 'h' -> 7;
            case 'i' -> 8;
            case 'j' -> 9;
            default -> throw new IllegalStateException("Unexpected value: " + letter);
        };
    }

    public static int getYFromCoordinates(String coordinates) {

        if (coordinates.length() < 3)
            return Integer.parseInt(coordinates.substring(1, 2)) - 1;
        else
            return Integer.parseInt(coordinates.substring(1, 3)) - 1;

    }

    public static String getCoordinatesFromShipSet(String set) {

        if (!ValidationService.isShipSetValid(set))
            throw new IllegalStateException("Unexpected value" + set);

        String coordinates = "";

        if (set.length() == 4) {
            coordinates = set.substring(0, 2);
        }

        if (set.length() == 5) {
            coordinates = set.substring(0, 3);
        }

        return coordinates;
    }

}
