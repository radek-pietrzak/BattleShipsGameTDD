package org.ships;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicBoolean;

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

    public String addShipToMatrix(Ship ship, String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        List<String> encodedShip = Ship.encodeShipToCoordinates(ship, coordinates);

        if (shipIsNotInBounds(encodedShip))
            return "Ship out of bounds.";

        if (shipIsOnAnother(encodedShip))
            return "Ship on another.";

        if (shipIsToCloseToAnother(encodedShip))
            return "Ship to close to another.";

        encodedShip.forEach(c -> {
            String type = c.substring(0, 1);
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            matrix[y][x] = type;

            addOffset(x, y);

        });

        return "Ship added.";
    }

    public String shoot(String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        int x = getFirstNumberFromPosition(coordinates);
        int y = getSecondNumberFromPosition(coordinates);

        if (isShotHit(x, y)) {
            matrix[y][x] = "X";
            return "Hit!!";
        }

        matrix[y][x] = ".";

        return "Missed.";
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

    private boolean shipIsOnAnother(List<String> encodedShip) {

        AtomicBoolean result = new AtomicBoolean(false);

        encodedShip.forEach(c -> {
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            if (areCoordinatesOccupied(getMatrix()[y][x]))
                result.set(true);

        });

        return result.get();
    }

    private boolean shipIsToCloseToAnother(List<String> encodedShip) {

        AtomicBoolean result = new AtomicBoolean(false);

        encodedShip.forEach(c -> {
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            if (getMatrix()[y][x].equals("!"))
                result.set(true);

        });

        return result.get();
    }

    private void addOffset(int x, int y) {

        if (x - 1 >= 0 && y - 1 >= 0)
            if (!areCoordinatesOccupied(getMatrix()[y - 1][x - 1]))
                matrix[y - 1][x - 1] = "!";

        if (y - 1 >= 0)
            if (!areCoordinatesOccupied(getMatrix()[y - 1][x]))
                matrix[y - 1][x] = "!";

        if (x + 1 < 10 && y - 1 >= 0)
            if (!areCoordinatesOccupied(getMatrix()[y - 1][x + 1]))
                matrix[y - 1][x + 1] = "!";

        if (x - 1 >= 0)
            if (!areCoordinatesOccupied(getMatrix()[y][x - 1]))
                matrix[y][x - 1] = "!";

        if (x + 1 < 10)
            if (!areCoordinatesOccupied(getMatrix()[y][x + 1]))
                matrix[y][x + 1] = "!";

        if (x - 1 >= 0 && y + 1 < 10)
            if (!areCoordinatesOccupied(getMatrix()[y + 1][x - 1]))
                matrix[y + 1][x - 1] = "!";

        if (y + 1 < 10)
            if (!areCoordinatesOccupied(getMatrix()[y + 1][x]))
                matrix[y + 1][x] = "!";

        if (x + 1 < 10 && y + 1 < 10)
            if (!areCoordinatesOccupied(getMatrix()[y + 1][x + 1]))
                matrix[y + 1][x + 1] = "!";

    }

    private boolean areCoordinatesOccupied(String m) {
        return m.equals("C") || m.equals("B") || m.equals("D") || m.equals("S") || m.equals("P");
    }

    private boolean isShotHit(int x, int y) {
        return getMatrix()[y][x].equals("C") ||
                getMatrix()[y][x].equals("B") ||
                getMatrix()[y][x].equals("D") ||
                getMatrix()[y][x].equals("S") ||
                getMatrix()[y][x].equals("P");
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

