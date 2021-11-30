package org.ships.service;

import org.ships.Matrix;
import org.ships.ship.Ship;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public class ShipService {

    public String addShipToMatrix(Ship ship, Matrix matrix, String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        List<String> encodedShip = Ship.encodeShipToCoordinates(ship, coordinates);

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

    private boolean shipIsNotInBounds(List<String> encodedShip) {

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

    private boolean shipIsOnAnother(List<String> encodedShip, Matrix matrix) {

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

    private boolean shipIsToCloseToAnother(List<String> encodedShip, Matrix matrix) {

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

    private void addOffset(int x, int y, Matrix matrix) {

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

    private boolean areCoordinatesOccupied(String m) {
        return m.equals("C") || m.equals("B") || m.equals("D") || m.equals("S") || m.equals("P");
    }

}
