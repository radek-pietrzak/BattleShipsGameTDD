package org.ships.service;

import org.junit.jupiter.api.Test;
import org.ships.Matrix;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class ShootServiceTest {

    private final ShootService shootService = new ShootService();
    private final Matrix matrix = new Matrix();

    @Test
    void shouldShootAddDotToMatrix() {

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
        shootService.shoot(matrix, "b2");
        shootService.shoot(matrix, "b3");

        shootService.shoot(matrix, "d2");
        shootService.shoot(matrix, "d3");
        shootService.shoot(matrix, "d4");

        shootService.shoot(matrix, "h4");
        shootService.shoot(matrix, "i4");
        shootService.shoot(matrix, "j4");

        shootService.shoot(matrix, "d9");
        shootService.shoot(matrix, "d10");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

    @Test
    void shouldReturnMissed() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Missed.";

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
        assertEquals(result, shootService.shoot(matrix, "b1"));
        assertEquals(result, shootService.shoot(matrix, "a10"));
        assertEquals(result, shootService.shoot(matrix, "g5"));

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
        assertEquals(result, shootService.shoot(matrix, "b2"));
        assertEquals(result, shootService.shoot(matrix, "d3"));
        assertEquals(result, shootService.shoot(matrix, "i9"));
        assertEquals(result, shootService.shoot(matrix, "j4"));


    }

}