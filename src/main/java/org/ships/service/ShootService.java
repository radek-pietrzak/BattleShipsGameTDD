package org.ships.service;

import org.ships.Matrix;

import java.util.ArrayList;
import java.util.List;

public class ShootService {

    public static String shoot(Matrix matrix, String coordinates) {

        if (!ValidationService.areCoordinatesValid(coordinates))
            return "Incorrect coordinates";

        int x = CoordinatesService.getXFromCoordinates(coordinates);
        int y = CoordinatesService.getYFromCoordinates(coordinates);

        if (isShipInCoordinates(x, y, matrix)) {
            matrix.addToMatrix(x, y, "X");
            addEmptyCoordinatesAfterHit(x, y, matrix);
            return "Hit!!";
        }

        matrix.addToMatrix(x, y, ".");

        return "Missed.";
    }

    private static void addEmptyCoordinatesAfterHit(int x, int y, Matrix matrix) {
        matrix.addToMatrix(x - 1, y - 1, ".");
        matrix.addToMatrix(x + 1, y - 1, ".");
        matrix.addToMatrix(x - 1, y + 1, ".");
        matrix.addToMatrix(x + 1, y + 1, ".");

        if (isShipSunk(x, y, matrix)) {
            addEmptyCoordinatesAfterSunk(x, y, matrix);
        }
    }

    private static void addEmptyCoordinatesAfterSunk(int x, int y, Matrix matrix) {

        List<Integer> initialCoordinatesOfHitsAround = getCoordinatesOfHitsAround(x, y, matrix);

        List<Integer> coordinatesOfHitsAround = initialCoordinatesOfHitsAround;

        int lastCheckedX = x;
        int lastCheckedY = y;
        int checkedX = x;
        int checkedY = y;

        while (coordinatesOfHitsAround.size() == 4) {
            lastCheckedX = checkedX;
            lastCheckedY = checkedY;
            checkedX = coordinatesOfHitsAround.get(0);
            checkedY = coordinatesOfHitsAround.get(1);
            coordinatesOfHitsAround = getCoordinatesOfHitsAround(checkedX, checkedY, matrix);
        }

        if (coordinatesOfHitsAround.size() == 2) {
            checkedX = coordinatesOfHitsAround.get(0);
            checkedY = coordinatesOfHitsAround.get(1);
        }

        matrix.addToMatrix(checkedX + checkedX - lastCheckedX, checkedY + checkedY - lastCheckedY, ".");

        coordinatesOfHitsAround = initialCoordinatesOfHitsAround;

        lastCheckedX = x;
        lastCheckedY = y;
        checkedX = x;
        checkedY = y;

        while (coordinatesOfHitsAround.size() == 4) {
            lastCheckedX = checkedX;
            lastCheckedY = checkedY;
            checkedX = coordinatesOfHitsAround.get(2);
            checkedY = coordinatesOfHitsAround.get(3);
            coordinatesOfHitsAround = getCoordinatesOfHitsAround(checkedX, checkedY, matrix);
        }

        if (coordinatesOfHitsAround.size() == 2) {
            checkedX = coordinatesOfHitsAround.get(0);
            checkedY = coordinatesOfHitsAround.get(1);
        }

        matrix.addToMatrix(checkedX + checkedX - lastCheckedX, checkedY + checkedY - lastCheckedY, ".");

    }

    private static List<Integer> getCoordinatesOfHitsAround(int x, int y, Matrix matrix) {
        List<Integer> result = new ArrayList<>();

        if (isHitInCoordinates(x + 1, y, matrix)) {
            result.add(x + 1);
            result.add(y);
        }

        if (isHitInCoordinates(x - 1, y, matrix)) {
            result.add(x - 1);
            result.add(y);
        }

        if (isHitInCoordinates(x, y + 1, matrix)) {
            result.add(x);
            result.add(y + 1);
        }

        if (isHitInCoordinates(x, y - 1, matrix)) {
            result.add(x);
            result.add(y - 1);
        }

        return result;
    }

    private static boolean isShipSunk(int x, int y, Matrix matrix) {

        if (isShipAround(x, y, matrix))
            return false;

        int checkX = x;
        checkX += 1;

        while (isHitInCoordinates(checkX, y, matrix)) {
            if (isShipAround(checkX, y, matrix))
                return false;

            checkX += 1;
        }

        checkX = x - 1;

        while (isHitInCoordinates(checkX, y, matrix)) {
            if (isShipAround(checkX, y, matrix))
                return false;

            checkX -= 1;
        }

        int checkY = y;
        checkY += 1;

        while (isHitInCoordinates(x, checkY, matrix)) {
            if (isShipAround(x, checkY, matrix))
                return false;

            checkY += 1;
        }

        checkY = y - 1;

        while (isHitInCoordinates(x, checkY, matrix)) {
            if (isShipAround(x, checkY, matrix))
                return false;

            checkY -= 1;
        }

        return true;
    }

    private static boolean isShipAround(int x, int y, Matrix matrix) {
        return isShipInCoordinates(x + 1, y, matrix) ||
                isShipInCoordinates(x - 1, y, matrix) ||
                isShipInCoordinates(x, y + 1, matrix) ||
                isShipInCoordinates(x, y - 1, matrix);
    }

    private static boolean isHitInCoordinates(int x, int y, Matrix matrix) {
        return matrix.getMatrix()[y][x].equals("X");
    }

    private static boolean isShipInCoordinates(int x, int y, Matrix matrix) {
        if (x >= 0 && x < 10 && y >= 0 && y < 10)
            return matrix.getMatrix()[y][x].equals("C") ||
                    matrix.getMatrix()[y][x].equals("B") ||
                    matrix.getMatrix()[y][x].equals("D") ||
                    matrix.getMatrix()[y][x].equals("S") ||
                    matrix.getMatrix()[y][x].equals("P");

        return false;
    }
}
