package org.ships;

import org.ships.service.CoordinatesService;
import org.ships.service.ValidationService;

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

    public String shoot(String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        int x = CoordinatesService.getXFromCoordinates(coordinates);
        int y = CoordinatesService.getYFromCoordinates(coordinates);

        if (isShotHit(x, y)) {
            matrix[y][x] = "X";
            return "Hit!!";
        }

        matrix[y][x] = ".";

        return "Missed.";
    }

    private boolean isShotHit(int x, int y) {
        return getMatrix()[y][x].equals("C") ||
                getMatrix()[y][x].equals("B") ||
                getMatrix()[y][x].equals("D") ||
                getMatrix()[y][x].equals("S") ||
                getMatrix()[y][x].equals("P");
    }


}

