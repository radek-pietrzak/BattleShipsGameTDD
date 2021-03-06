package org.ships.service;

import org.ships.Matrix;
import org.ships.Ship;
import org.ships.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class FleetService {

    public static List<String> encodeShipWithCoordinatesAndVector(Ship ship, Vector vector, String coordinates) {

        List<String> result = new ArrayList<>();

        int shipLength;
        String shipType;

        switch (ship) {
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
            default -> throw new IllegalStateException("Unexpected value: " + ship);
        }

        int x = CoordinatesService.getXFromCoordinates(coordinates);
        int y = CoordinatesService.getYFromCoordinates(coordinates);

        result.add(shipType + "." + x + "," + y);

        for (int i = 0; i < shipLength - 1; i++) {

            switch (vector) {
                case NORTH -> y -= 1;
                case SOUTH -> y += 1;
                case WEST -> x -= 1;
                case EAST -> x += 1;
            }

            result.add(shipType + "." + x + "," + y);
        }

        return result;
    }

    public static String addEncodedShipToMatrix(List<String> encodedShip, Matrix matrix) {

        if (shipIsNotInBounds(encodedShip))
            return "Ship out of bounds.ERROR";

        if (shipIsOnAnother(encodedShip, matrix))
            return "Ship on another.ERROR";

        if (shipIsToCloseToAnother(encodedShip, matrix))
            return "Ship too close to another.ERROR";

        encodedShip.forEach(c -> {
            String type = c.substring(0, 1);
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            matrix.getMatrix()[y][x] = type;

            addOffset(x, y, matrix);

        });

        return "Ship added.";
    }

    public static int getShipLength(Ship ship) {
        int shipLength;

        switch (ship) {
            case CARRIER -> shipLength = 5;
            case BATTLESHIP -> shipLength = 4;
            case DESTROYER, SUBMARINE -> shipLength = 3;
            case PATROL_BOAT -> shipLength = 2;
            default -> throw new IllegalStateException("Unexpected value: " + ship);
        }
        return shipLength;
    }

    public static String encodeShipFromCoordinates(int x, int y, Matrix matrix) {

        return matrix.getMatrix()[y][x] + "." + x + "," + y;
    }

    public static String encodeShipToCoordinates(Ship ship, int x, int y) {

        String symbol = switch (ship) {
            case CARRIER -> "C";
            case BATTLESHIP -> "B";
            case DESTROYER -> "D";
            case SUBMARINE -> "S";
            case PATROL_BOAT -> "P";
        };

        return symbol + "." + x + "," + y;
    }

    public static int[] decodePositionToCoordinates(String position) {

        if (!ValidationService.isEncodedPositionValid(position))
            throw new IllegalArgumentException();

        int dot = position.indexOf(".");
        int comma = position.indexOf(",");

        int x = Integer.parseInt(position.substring(dot + 1, comma));
        int y = Integer.parseInt(position.substring(comma + 1));

        return new int[]{x, y};
    }

    private static boolean shipIsNotInBounds(List<String> encodedShip) {

        AtomicBoolean result = new AtomicBoolean(false);

        encodedShip.forEach(c -> {
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            if (x < 0 || x > 9 || y < 0 || y > 9)
                result.set(true);

        });

        return result.get();
    }

    private static boolean shipIsOnAnother(List<String> encodedShip, Matrix matrix) {

        AtomicBoolean result = new AtomicBoolean(false);

        encodedShip.forEach(c -> {
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            if (isShipInCoordinates(matrix.getMatrix()[y][x]))
                result.set(true);

        });

        return result.get();
    }

    private static boolean shipIsToCloseToAnother(List<String> encodedShip, Matrix matrix) {

        AtomicBoolean result = new AtomicBoolean(false);

        encodedShip.forEach(c -> {
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            if (matrix.getMatrix()[y][x].equals("!"))
                result.set(true);

        });

        return result.get();
    }

    private static void addOffset(int x, int y, Matrix matrix) {

        if (x - 1 >= 0 && y - 1 >= 0)
            if (!isShipInCoordinates(matrix.getMatrix()[y - 1][x - 1]))
                matrix.getMatrix()[y - 1][x - 1] = "!";

        if (y - 1 >= 0)
            if (!isShipInCoordinates(matrix.getMatrix()[y - 1][x]))
                matrix.getMatrix()[y - 1][x] = "!";

        if (x + 1 < 10 && y - 1 >= 0)
            if (!isShipInCoordinates(matrix.getMatrix()[y - 1][x + 1]))
                matrix.getMatrix()[y - 1][x + 1] = "!";

        if (x - 1 >= 0)
            if (!isShipInCoordinates(matrix.getMatrix()[y][x - 1]))
                matrix.getMatrix()[y][x - 1] = "!";

        if (x + 1 < 10)
            if (!isShipInCoordinates(matrix.getMatrix()[y][x + 1]))
                matrix.getMatrix()[y][x + 1] = "!";

        if (x - 1 >= 0 && y + 1 < 10)
            if (!isShipInCoordinates(matrix.getMatrix()[y + 1][x - 1]))
                matrix.getMatrix()[y + 1][x - 1] = "!";

        if (y + 1 < 10)
            if (!isShipInCoordinates(matrix.getMatrix()[y + 1][x]))
                matrix.getMatrix()[y + 1][x] = "!";

        if (x + 1 < 10 && y + 1 < 10)
            if (!isShipInCoordinates(matrix.getMatrix()[y + 1][x + 1]))
                matrix.getMatrix()[y + 1][x + 1] = "!";

    }

    private static boolean isShipInCoordinates(String symbol) {
        return symbol.equals("C") || symbol.equals("B") || symbol.equals("D") || symbol.equals("S") || symbol.equals("P");
    }

}
