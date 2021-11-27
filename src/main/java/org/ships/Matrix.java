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

    public void addShipToMatrix(Ship ship, String position) {

        Vector vector = ship.getVector();
        ShipType shipType = ship.getShipType();
        String typeSymbol;
        int shipLength;

        switch (shipType) {
            case PATROL_BOAT -> {
                typeSymbol = "P";
                shipLength = 2;
                addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case SUBMARINE -> {
                typeSymbol = "S";
                shipLength = 3;
                addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case DESTROYER -> {
                typeSymbol = "D";
                shipLength = 3;
                addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case BATTLESHIP -> {
                typeSymbol = "B";
                shipLength = 4;
                addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }
            case CARRIER -> {
                typeSymbol = "C";
                shipLength = 5;
                addSymbolsToMatrix(vector, position, typeSymbol, shipLength);
            }


        }

    }

    private void addSymbolsToMatrix(Vector vector, String position, String typeSymbol, int shipLength) {
        int x = getFirstNumberFromPosition(position);
        int y = getSecondNumberFromPosition(position);

        for (int i = 0; i < shipLength; i++) {

            switch (vector) {
                case NORTH -> matrix[y - i][x] = typeSymbol;
                case SOUTH -> matrix[y + i][x] = typeSymbol;
                case WEST -> matrix[y][x - i] = typeSymbol;
                case EAST -> matrix[y][x + i] = typeSymbol;
            }
        }
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

