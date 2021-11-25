package org.ships;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    Matrix matrix = new Matrix();

    @Test
    void shouldAddElementToMatrix(){

        //given
        String[][] result = {
                {"X", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", "X", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", "X", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", "X",}
        };

        //when
        matrix.addToMatrix("a1");
        matrix.addToMatrix("b3");
        matrix.addToMatrix("d4");
        matrix.addToMatrix("J10");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

}