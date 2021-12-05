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
            String hitPart = FleetService.encodeShipFromCoordinates(x, y, matrix);
            matrix.addToMatrix(x, y, "X");
            addEmptyCoordinatesAfterHit(x, y, matrix);
            return hitPart;
        }

        if (!isHitInCoordinates(x, y, matrix))
            matrix.addToMatrix(x, y, ".");

        return "Missed.";
    }

    public static String shootComp(Matrix matrix, int x, int y) {

        if (isShipInCoordinates(x, y, matrix)) {
            String hitPart = FleetService.encodeShipFromCoordinates(x, y, matrix);
            matrix.addToMatrix(x, y, "X");
            addEmptyCoordinatesAfterHit(x, y, matrix);
            return hitPart;
        }

        if (!isHitInCoordinates(x, y, matrix))
            matrix.addToMatrix(x, y, ".");

        return "Computer missed.";
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

        List<List<Integer>> allHitList = getAllHitsAfterSunk(x, y, matrix);

        allHitList.stream()
                .distinct()
                .forEach(list -> addEmptyCoordinatesAroundIfOffset(list.get(0), list.get(1), matrix));

    }

    private static void addEmptyCoordinatesAroundIfOffset(Integer x, Integer y, Matrix matrix) {

        if (x + 1 < 10)
            if (matrix.getMatrix()[y][x + 1].equals("!"))
                matrix.addToMatrix(x + 1, y, ".");

        if (x - 1 >= 0)
            if (matrix.getMatrix()[y][x - 1].equals("!"))
                matrix.addToMatrix(x - 1, y, ".");

        if (y + 1 < 10)
            if (matrix.getMatrix()[y + 1][x].equals("!"))
                matrix.addToMatrix(x, y + 1, ".");

        if (y - 1 >= 0)
            if (matrix.getMatrix()[y - 1][x].equals("!"))
                matrix.addToMatrix(x, y - 1, ".");


    }


    private static List<List<Integer>> getAllHitsAfterSunk(int x, int y, Matrix matrix) {

        List<List<Integer>> result;

        result = getAllHitsAround(x, y, matrix);

        int checkX = x + 1;

        while (isHitInCoordinates(checkX, y, matrix)) {
            result.addAll(getAllHitsAround(checkX, y, matrix));
            checkX += 1;
        }

        checkX = x - 1;

        while (isHitInCoordinates(checkX, y, matrix)) {
            result.addAll(getAllHitsAround(checkX, y, matrix));
            checkX -= 1;
        }

        int checkY = y + 1;

        while (isHitInCoordinates(x, checkY, matrix)) {
            result.addAll(getAllHitsAround(x, checkY, matrix));
            checkY += 1;
        }

        checkY = y - 1;

        while (isHitInCoordinates(x, checkY, matrix)) {
            result.addAll(getAllHitsAround(x, checkY, matrix));
            checkY -= 1;
        }

        return result;
    }

    private static List<List<Integer>> getAllHitsAround(int x, int y, Matrix matrix) {

        List<List<Integer>> result = new ArrayList<>();

        if (isHitInCoordinates(x + 1, y, matrix)) {
            List<Integer> hitCoordinates = new ArrayList<>();
            hitCoordinates.add(x + 1);
            hitCoordinates.add(y);
            result.add(hitCoordinates);
        }

        if (isHitInCoordinates(x - 1, y, matrix)) {
            List<Integer> hitCoordinates = new ArrayList<>();
            hitCoordinates.add(x - 1);
            hitCoordinates.add(y);
            result.add(hitCoordinates);
        }

        if (isHitInCoordinates(x, y + 1, matrix)) {
            List<Integer> hitCoordinates = new ArrayList<>();
            hitCoordinates.add(x);
            hitCoordinates.add(y + 1);
            result.add(hitCoordinates);
        }

        if (isHitInCoordinates(x, y - 1, matrix)) {
            List<Integer> hitCoordinates = new ArrayList<>();
            hitCoordinates.add(x);
            hitCoordinates.add(y - 1);
            result.add(hitCoordinates);
        }

        return result;
    }


    private static boolean isShipSunk(int x, int y, Matrix matrix) {

        if (isShipAround(x, y, matrix))
            return false;

        int checkX = x + 1;

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

        int checkY = y + 1;

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
        if (x < 10 && x >= 0 && y < 10 && y >= 0)
            return matrix.getMatrix()[y][x].equals("X");
        else
            return false;
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
