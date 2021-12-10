package org.ships;

import org.junit.jupiter.api.Test;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class ComputerTest {

    private final Matrix matrix = new Matrix();
    private final Computer computer = new Computer();

    @Test
    void totallyRandomAlgorithmShouldFillAllMatrixWithDots() {

        //given
        Algorithm algorithm = Algorithm.TOTALLY_RANDOM;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

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
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void randomWithFinishingAlgorithmShouldFillAllMatrixWithDots() {

        //given
        Algorithm algorithm = Algorithm.RANDOM_WITH_FINISHING;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

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
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void randomWithFinishingAlgorithmShouldFillSurroundsOfXesFirst() {

        //given
        Algorithm algorithm = Algorithm.RANDOM_WITH_FINISHING;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");
        matrix.addToMatrix(9, 9, "X");

        matrix.addToMatrix(1, 5, "X");
        matrix.addToMatrix(1, 6, "X");

        matrix.addToMatrix(0, 4, ".");
        matrix.addToMatrix(0, 5, ".");
        matrix.addToMatrix(0, 6, ".");
        matrix.addToMatrix(0, 7, ".");

        matrix.addToMatrix(2, 4, ".");
        matrix.addToMatrix(2, 5, ".");
        matrix.addToMatrix(2, 6, ".");
        matrix.addToMatrix(2, 7, ".");

        matrix.addToMatrix(6, 0, "X");
        matrix.addToMatrix(7, 0, "X");
        matrix.addToMatrix(8, 0, "X");

        matrix.addToMatrix(5, 1, ".");
        matrix.addToMatrix(6, 1, ".");
        matrix.addToMatrix(7, 1, ".");
        matrix.addToMatrix(8, 1, ".");
        matrix.addToMatrix(9, 1, ".");

        String[][] result = {
                {" ", ".", " ", " ", " ", ".", "X", "X", "X", ".",},
                {".", "X", ".", " ", " ", ".", ".", ".", ".", ".",},
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {".", ".", ".", " ", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", ".", "X", ".", " ", " ", " ", " ",},
                {".", "X", ".", " ", ".", " ", " ", " ", " ", " ",},
                {".", ".", ".", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", ".",},
                {" ", " ", " ", " ", " ", " ", " ", " ", ".", "X",}
        };

        //when
        for (int i = 0; i < 14; i++) {
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void finishingEmptySurroundLvl1AlgorithmShouldFinishAllHits() {

        //given
        Algorithm algorithm = Algorithm.FINISHING_EMPTY_SURROUND_LVL1;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

        String[][] result = {
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",},
                {".", "X", ".", " ", " ", " ", " ", " ", " ", " ",},
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", ".", " ", " ", " ", " ", " ",},
                {" ", " ", " ", ".", "X", ".", " ", " ", " ", " ",},
                {" ", " ", " ", " ", ".", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}
        };

        //when
        for (int i = 0; i < 8; i++) {
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void finishingEmptySurroundLvl1AlgorithmShouldFillAllMatrixWithDots() {

        //given
        Algorithm algorithm = Algorithm.FINISHING_EMPTY_SURROUND_LVL1;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

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
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void checkAllPossiblePositionsAlgorithmShouldFinishAllHits() {

        //given
        Algorithm algorithm = Algorithm.CHECK_ALL_POSSIBLE_POSITIONS;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

        String[][] result = {
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",},
                {".", "X", ".", " ", " ", " ", " ", " ", " ", " ",},
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", ".", " ", " ", " ", " ", " ",},
                {" ", " ", " ", ".", "X", ".", " ", " ", " ", " ",},
                {" ", " ", " ", " ", ".", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}
        };

        //when
        for (int i = 0; i < 8; i++) {
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }

    @Test
    void checkAllPossiblePositionsAlgorithmShouldFillAllMatrixWithDots() {

        //given
        Algorithm algorithm = Algorithm.CHECK_ALL_POSSIBLE_POSITIONS;

        matrix.addToMatrix(1, 1, "X");
        matrix.addToMatrix(4, 5, "X");

        matrix.addToMatrix(0,0, ".");
        matrix.addToMatrix(2,0, ".");
        matrix.addToMatrix(0,2, ".");
        matrix.addToMatrix(2,2, ".");

        matrix.addToMatrix(3,4, ".");
        matrix.addToMatrix(5,4, ".");
        matrix.addToMatrix(3,6, ".");
        matrix.addToMatrix(5,6, ".");

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
        for (int i = 0; i < 90; i++) {
            int[] choice = computer.compShotChoice(matrix, algorithm);
            matrix.addToMatrix(choice[0], choice[1], ".");
        }

        //then
        assertThat(matrix.getMatrix(), equalTo(result));

    }


}