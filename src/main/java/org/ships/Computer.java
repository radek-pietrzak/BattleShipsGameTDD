package org.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer {

    public int[] compShotChoice(Matrix matrix, Algorithm algorithm) {

        return switch (algorithm) {
            case TOTALLY_RANDOM -> totallyRandomAlgorithm(matrix);
            case RANDOM_WITH_FINISHING -> randomWithFinishingAlgorithm(matrix);
            case FINISHING_EMPTY_SURROUND_LVL1 -> finishingEmptySurroundLvl1(matrix);
        };

    }

    private int[] finishingEmptySurroundLvl1(Matrix matrix) {

        int[] result = finishingAlgorithm(matrix);

        if (result != null)
            return result;

        return emptySurroundLvl1(matrix);

    }

    private int[] emptySurroundLvl1(Matrix matrix) {

        int numberOfFreeSpaces = 8;

        List<List<Integer>> shotList = new ArrayList<>();

        while (numberOfFreeSpaces > 0) {

            for (int i = 0; i < 10; i++)
                for (int j = 0; j < 10; j++)
                    if (matrix.getMatrix()[j][i].equals(" "))
                        shotList.add(checkSpacesAround(i, j, matrix, numberOfFreeSpaces));

            shotList.removeIf(List::isEmpty);

            if (shotList.isEmpty())
                numberOfFreeSpaces--;
            else
                break;
        }

        if (!shotList.isEmpty()) {
            Random random = new Random();
            int shotIndex = random.nextInt(shotList.size());
            var shot = shotList.get(shotIndex);

            return new int[]{shot.get(0), shot.get(1)};
        } else
            return totallyRandomAlgorithm(matrix);
    }

    private List<Integer> checkSpacesAround(int x, int y, Matrix matrix, int numberOfFreeSpaces) {

        List<Integer> coordinates = new ArrayList<>();

        int count = 0;

        for (int i = -1; i < 2; i++)
            for (int j = -1; j < 2; j++)
                if (x + i < 10 && x + i >= 0 && y + j < 10 && y + j >= 0)
                    if (!(x + i == x && y + j == y))
                        if (matrix.getMatrix()[y + j][x + i].equals(" "))
                            count++;

        if (count == numberOfFreeSpaces) {
            coordinates.add(x);
            coordinates.add(y);
        }

        return coordinates;

    }


    private int[] randomWithFinishingAlgorithm(Matrix matrix) {

        int[] result = finishingAlgorithm(matrix);
        if (result != null) return result;

        return totallyRandomAlgorithm(matrix);
    }

    private int[] finishingAlgorithm(Matrix matrix) {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (matrix.getMatrix()[j][i].equals("X")) {
                    int[] result = checkSurroundForFirstShot(matrix, i, j);
                    if (result != null)
                        return result;
                }
        return null;
    }

    private int[] checkSurroundForFirstShot(Matrix matrix, int x, int y) {

        int[] result;

        result = checkSurroundIfAnotherHitForFirstShot(matrix, x, y);

        List<List<Integer>> shotList = new ArrayList<>();

        if (null == result) {

            if (x + 1 < 10)
                if (matrix.getMatrix()[y][x + 1].equals(" "))
                    shotList.add(List.of(x + 1, y));

            if (x - 1 >= 0)
                if (matrix.getMatrix()[y][x - 1].equals(" "))
                    shotList.add(List.of(x - 1, y));

            if (y + 1 < 10)
                if (matrix.getMatrix()[y + 1][x].equals(" "))
                    shotList.add(List.of(x, y + 1));

            if (y - 1 >= 0)
                if (matrix.getMatrix()[y - 1][x].equals(" "))
                    shotList.add(List.of(x, y - 1));

            if (shotList.size() != 0) {

                Random random = new Random();
                int shot = random.nextInt(shotList.size());

                result = new int[]{shotList.get(shot).get(0), shotList.get(shot).get(1)};
            }
        }

        return result;
    }

    private int[] checkSurroundIfAnotherHitForFirstShot(Matrix matrix, int x, int y) {

        if (x + 1 < 10)
            if (matrix.getMatrix()[y][x + 1].equals("X"))
                if (x - 1 >= 0)
                    if (matrix.getMatrix()[y][x - 1].equals(" "))
                        return new int[]{x - 1, y};

        if (x - 1 >= 0)
            if (matrix.getMatrix()[y][x - 1].equals("X"))
                if (x + 1 < 10)
                    if (matrix.getMatrix()[y][x + 1].equals(" "))
                        return new int[]{x + 1, y};

        if (y + 1 < 10)
            if (matrix.getMatrix()[y + 1][x].equals("X"))
                if (y - 1 >= 0)
                    if (matrix.getMatrix()[y - 1][x].equals(" "))
                        return new int[]{x, y - 1};

        if (y - 1 >= 0)
            if (matrix.getMatrix()[y - 1][x].equals("X"))
                if (y + 1 < 10)
                    if (matrix.getMatrix()[y + 1][x].equals(" "))
                        return new int[]{x, y + 1};

        return null;
    }


    private int[] totallyRandomAlgorithm(Matrix matrix) {

        Random random = new Random();

        int x;
        int y;

        do {

            x = random.nextInt(10);
            y = random.nextInt(10);

        } while (matrix.getMatrix()[y][x].equals(".") ||
                matrix.getMatrix()[y][x].equals("X"));

        return new int[]{x, y};
    }
}
