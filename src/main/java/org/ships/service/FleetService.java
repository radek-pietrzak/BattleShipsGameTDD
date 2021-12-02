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

    public static String addEncodedShipToMatrix(List<String > encodedShip, Matrix matrix) {

        if (shipIsNotInBounds(encodedShip))
            return "Ship out of bounds.";

        if (shipIsOnAnother(encodedShip, matrix))
            return "Ship on another.";

        if (shipIsToCloseToAnother(encodedShip, matrix))
            return "Ship too close to another.";

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

            if (areCoordinatesOccupied(matrix.getMatrix()[y][x]))
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
            if (!areCoordinatesOccupied(matrix.getMatrix()[y - 1][x - 1]))
                matrix.getMatrix()[y - 1][x - 1] = "!";

        if (y - 1 >= 0)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y - 1][x]))
                matrix.getMatrix()[y - 1][x] = "!";

        if (x + 1 < 10 && y - 1 >= 0)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y - 1][x + 1]))
                matrix.getMatrix()[y - 1][x + 1] = "!";

        if (x - 1 >= 0)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y][x - 1]))
                matrix.getMatrix()[y][x - 1] = "!";

        if (x + 1 < 10)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y][x + 1]))
                matrix.getMatrix()[y][x + 1] = "!";

        if (x - 1 >= 0 && y + 1 < 10)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y + 1][x - 1]))
                matrix.getMatrix()[y + 1][x - 1] = "!";

        if (y + 1 < 10)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y + 1][x]))
                matrix.getMatrix()[y + 1][x] = "!";

        if (x + 1 < 10 && y + 1 < 10)
            if (!areCoordinatesOccupied(matrix.getMatrix()[y + 1][x + 1]))
                matrix.getMatrix()[y + 1][x + 1] = "!";

    }

    private static boolean areCoordinatesOccupied(String m) {
        return m.equals("C") || m.equals("B") || m.equals("D") || m.equals("S") || m.equals("P");
    }

}
