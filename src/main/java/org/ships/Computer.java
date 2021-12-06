package org.ships;

import java.util.Random;

public class Computer {

    private final Random random = new Random();

    public int[] compShotChoice(Matrix matrix) {
        return totallyRandomAlgorithm(matrix);
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
