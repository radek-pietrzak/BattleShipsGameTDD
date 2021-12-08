package org.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Computer {

    private final Random random = new Random();

    public int[] compShotChoice(Matrix matrix, Algorithm algorithm) {

        return switch (algorithm) {
            case TOTALLY_RANDOM -> totallyRandomAlgorithm(matrix);
            case RANDOM_WITH_FINISHING -> randomWithFinishingAlgorithm(matrix);
            case EMPTY_SURROUND_FIRST_SHOT -> null;
        };

    }

    private int[] randomWithFinishingAlgorithm(Matrix matrix) {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++)
                if (matrix.getMatrix()[j][i].equals("X")) {
                    int[] result = checkSurroundForFirstShot(matrix, i, j);
                    if (result != null)
                        return result;
                }

        return totallyRandomAlgorithm(matrix);
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
