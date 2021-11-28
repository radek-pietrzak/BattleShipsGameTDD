package org.ships;

import java.util.Locale;

public class Matrix {

    private final String[][] matrix = {
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}
    };

    public String[][] getMatrix() {
        return matrix;
    }

    public String addShipToMatrix(Ship ship, String position) {

        if (!positionIsValid(position))
            return "Incorrect position";

        Vector vector = ship.getVector();
        ShipType shipType = ship.getShipType();

        return recognizeAndFindPlaceToShip(position, vector, shipType);

    }

    private boolean positionIsValid(String position) {

        if (position.length() < 2 || position.length() > 3)
            return false;


        if(!position.toLowerCase(Locale.ROOT).substring(0, 1).matches("[a-j]"))
            return false;

        boolean isXAxisValid;

        if (position.length() == 3)
            isXAxisValid = position.startsWith("10", 1);
        else
            isXAxisValid = position.substring(1, 2).matches("[1-9]");


        return isXAxisValid;
    }

    private String recognizeAndFindPlaceToShip(String position, Vector vector, ShipType shipType) {
        String typeSymbol;
        int shipLength;

        switch (shipType) {
            case PATROL_BOAT -> {
                typeSymbol = "P";
                shipLength = 2;
                return addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case SUBMARINE -> {
                typeSymbol = "S";
                shipLength = 3;
                return addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case DESTROYER -> {
                typeSymbol = "D";
                shipLength = 3;
                return addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case BATTLESHIP -> {
                typeSymbol = "B";
                shipLength = 4;
                return addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case CARRIER -> {
                typeSymbol = "C";
                shipLength = 5;
                return addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
        }
        return "recognizeAndFindPlaceToShip failed";
    }

    private String addSymbolsToMatrix(Vector vector, String position, String typeSymbol, int shipLength) {

        int x = getFirstNumberFromPosition(position);
        int y = getSecondNumberFromPosition(position);

        matrix[y][x] = typeSymbol;

        switch (vector) {

            case NORTH -> {
                for (int i = 1; i < shipLength; i++) {
                    y -= 1;
                    if (y < 0)
                        return "Bad placement of the ship.";

                    matrix[y][x] = typeSymbol;
                }
            }

            case SOUTH -> {
                for (int i = 1; i < shipLength; i++) {
                    y += 1;
                    if (y > 9)
                        return "Bad placement of the ship.";

                    matrix[y][x] = typeSymbol;
                }
            }

            case WEST -> {
                for (int i = 1; i < shipLength; i++) {
                    x -= 1;
                    if (x < 0)
                        return "Bad placement of the ship.";

                    matrix[y][x] = typeSymbol;
                }
            }

            case EAST -> {
                for (int i = 1; i < shipLength; i++) {
                    x += 1;
                    if (x > 9)
                        return "Bad placement of the ship.";

                    matrix[y][x] = typeSymbol;
                }
            }
        }

        return "Ship added.";
    }

    private int getFirstNumberFromPosition(String position) {

        char letter = position.toLowerCase(Locale.ROOT).charAt(0);

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

    private int getSecondNumberFromPosition(String position) {

        if (position.length() < 3)
            return Integer.parseInt(position.substring(1, 2)) - 1;
        else
            return Integer.parseInt(position.substring(1, 3)) - 1;

    }
}

