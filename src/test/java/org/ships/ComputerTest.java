package org.ships;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ComputerTest {

    @Test
    void totallyRandomAlgorithmShouldFillAllMatrixWithDots() {

        //given
        Matrix matrix = new Matrix();
        Computer computer = new Computer();

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");
        matrix.addToMatrix(7, 8, "D");

        String[][] result = {
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", "X", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", "X", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",},
                {".", ".", ".", ".", ".", ".", ".", ".", ".", ".",}
        };

        //when
        for (int i = 0; i < 98; i++) {
            int[] choice = computer.compShotChoice(matrix);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

}