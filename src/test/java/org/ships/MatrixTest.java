package org.ships;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class MatrixTest {

    private final Matrix matrix = new Matrix();

    @Test
    void shouldProvideShootPositionToMatrix() {

        //given
        String[][] result = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", ".", " ", ".", " ", " ", " ", " ", " ", " ",},
                {" ", ".", " ", ".", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", ".", " ", " ", " ", ".", ".", ".",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", ".", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", ".", " ", " ", " ", " ", " ", " ",}
        };

        //when
        matrix.shoot("b2");
        matrix.shoot("b3");

        matrix.shoot("d2");
        matrix.shoot("d3");
        matrix.shoot("d4");

        matrix.shoot("h4");
        matrix.shoot("i4");
        matrix.shoot("j4");

        matrix.shoot("d9");
        matrix.shoot("d10");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));


    }

    @Test
    void shouldGetInfoIfShotIsValid() {

        //given
        String result = "Missed.";

        //when
        //then
        assertEquals(result, matrix.shoot("b1"));
        assertEquals(result, matrix.shoot("a10"));
        assertEquals(result, matrix.shoot("g5"));

    }

    @Test
    void shouldGetInfoIfShotHits() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Hit!!";

        String[][] givenMatrix = {
                {"!", "!", "!", "!", "!", " ", " ", " ", " ", " ",},
                {"!", "P", "!", "D", "!", " ", " ", " ", " ", " ",},
                {"!", "P", "!", "D", "!", " ", "!", "!", "!", "!",},
                {"!", "!", "!", "D", "!", " ", "!", "S", "S", "S",},
                {"!", "!", "!", "!", "!", "!", "!", "!", "!", "!",},
                {"C", "C", "C", "C", "C", "!", " ", "!", "B", "!",},
                {"!", "!", "!", "!", "!", "!", " ", "!", "B", "!",},
                {" ", " ", "!", "!", "!", "!", "!", "!", "B", "!",},
                {" ", " ", "!", "P", "!", "P", "P", "!", "B", "!",},
                {" ", " ", "!", "P", "!", "!", "!", "!", "!", "!",}
        };

        given(matrix.getMatrix()).willReturn(givenMatrix);

        //when
        //then
        assertEquals(result, matrix.shoot("b2"));
        assertEquals(result, matrix.shoot("d3"));
        assertEquals(result, matrix.shoot("i9"));
        assertEquals(result, matrix.shoot("j4"));


    }


}