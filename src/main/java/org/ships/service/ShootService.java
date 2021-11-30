package org.ships.service;

import org.ships.Matrix;

public class ShootService {

    public String shoot(Matrix matrix, String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        int x = CoordinatesService.getXFromCoordinates(coordinates);
        int y = CoordinatesService.getYFromCoordinates(coordinates);

        if (isShotHit(x, y, matrix)) {
            matrix.addToMatrix(x, y, "X");
            return "Hit!!";
        }

        matrix.addToMatrix(x, y, ".");

        return "Missed.";
    }

    private boolean isShotHit(int x, int y, Matrix matrix) {
        return matrix.getMatrix()[y][x].equals("C") ||
                matrix.getMatrix()[y][x].equals("B") ||
                matrix.getMatrix()[y][x].equals("D") ||
                matrix.getMatrix()[y][x].equals("S") ||
                matrix.getMatrix()[y][x].equals("P");
    }
}
