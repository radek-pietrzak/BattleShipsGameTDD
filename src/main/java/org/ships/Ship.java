package org.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Ship {

    private final ShipType shipType;
    private final Vector vector;

    public Ship(ShipType shipType, Vector vector) {
        this.shipType = shipType;
        this.vector = vector;
    }


    public ShipType getShipType() {
        return shipType;
    }

    public Vector getVector() {
        return vector;
    }

    public static List<String> encodeShipToCoordinates(Ship ship, String coordinates) {

        List<String> result = new ArrayList<>();

        int shipLength;
        String shipType;

        switch (ship.getShipType()) {
            case CARRIER -> {
                shipLength = 5;
                shipType = "C";
            }
            case BATTLESHIP -> {
                shipLength = 4;
                shipType = "B";
            }
            case DESTROYER -> {
                shipLength = 3;
                shipType = "D";
            }
            case SUBMARINE -> {
                shipLength = 3;
                shipType = "S";
            }
            case PATROL_BOAT -> {
                shipLength = 2;
                shipType = "P";
            }
            default -> throw new IllegalStateException("Unexpected value: " + ship.getShipType());
        }

        int x = getFirstNumberFromCoordinates(coordinates);
        int y = getSecondNumberFromCoordinates(coordinates);

        result.add(shipType + "." + x + "," + y);

        for (int i = 0; i < shipLength - 1; i++) {

            switch (ship.getVector()) {
                case NORTH -> y -= 1;
                case SOUTH -> y += 1;
                case WEST -> x -= 1;
                case EAST -> x += 1;
            }

            result.add(shipType + "." + x + "," + y);
        }

        return result;
    }

    private static int getFirstNumberFromCoordinates(String coordinates) {

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

    private static int getSecondNumberFromCoordinates(String coordinates) {

        if (coordinates.length() < 3)
            return Integer.parseInt(coordinates.substring(1, 2)) - 1;
        else
            return Integer.parseInt(coordinates.substring(1, 3)) - 1;

    }

}
