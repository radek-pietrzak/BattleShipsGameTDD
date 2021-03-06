package org.ships.service;

import org.ships.Vector;

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

        String coordinates = "";

        if (set.length() == 4) {
            coordinates = set.substring(0, 2);
        }

        if (set.length() == 5) {
            coordinates = set.substring(0, 3);
        }

        return coordinates;
    }

    public static Vector getVectorFromShipSet(String set) {

        Vector vector;

        String vectorString = "";

        if (set.length() == 4) {
            vectorString = set.substring(3);
        }

        if (set.length() == 5) {
            vectorString = set.substring(4);
        }

        switch (vectorString) {
            case "n" -> vector = Vector.NORTH;
            case "s" -> vector = Vector.SOUTH;
            case "w" -> vector = Vector.WEST;
            case "e" -> vector = Vector.EAST;
            default -> throw new IllegalStateException("Unexpected value: " + vectorString);
        }

        return vector;
    }

    public static String getCoordinatesFromXY(int x, int y) {

        StringBuilder result = new StringBuilder();

        switch (x) {
            case 0 -> result.append("a");
            case 1 -> result.append('b');
            case 2 -> result.append('c');
            case 3 -> result.append('d');
            case 4 -> result.append('e');
            case 5 -> result.append('f');
            case 6 -> result.append('g');
            case 7 -> result.append('h');
            case 8 -> result.append('i');
            case 9 -> result.append('j');
            default -> throw new IllegalStateException("Unexpected value: " + x);
        }

        result.append(y + 1);

        return result.toString();
    }

}
