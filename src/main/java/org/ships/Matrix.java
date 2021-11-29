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

        if (isPositionNotValid(coordinates))
            return "Incorrect coordinates";

        List<String> encodedShip = Ship.encodeShipToCoordinates(ship, coordinates);

        if (shipIsNotInBounds(encodedShip))
            return "Ship out of bounds.";

        if (shipIsOnAnotherShip(encodedShip))
            return "Ship on another.";

        encodedShip.forEach(c -> {
            String type = c.substring(0, 1);
            int dot = c.indexOf('.');
            int comma = c.indexOf(',');
            int x = Integer.parseInt(c.substring(dot + 1, comma));
            int y = Integer.parseInt(c.substring(comma + 1));

            matrix[y][x] = type;

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

    private boolean shipIsOnAnotherShip(List<String> encodedShip) {

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

    private boolean areCoordinatesOccupied(String m) {
        return m.equals("C") || m.equals("B") || m.equals("D") || m.equals("S") || m.equals("P");
    }

    public String shootPosition(String position) {

        if (isPositionNotValid(position))
            return "Incorrect position";

        int x = getFirstNumberFromPosition(position);
        int y = getSecondNumberFromPosition(position);

        matrix[y][x] = ".";

        return "Shoot!!";
    }

    private boolean isPositionNotValid(String position) {

        if (!(position.length() > 1))
            return true;

        if (!(position.length() < 4))
            return true;

        if (!position.toLowerCase(Locale.ROOT).substring(0, 1).matches("[a-j]"))
            return true;

        boolean isXAxisValid;

        if (position.length() == 3)
            isXAxisValid = position.startsWith("10", 1);
        else
            isXAxisValid = position.substring(1, 2).matches("[1-9]");


        return !isXAxisValid;
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

