package org.ships.service;

import org.junit.jupiter.api.Test;
import org.ships.Matrix;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class ShootServiceTest {

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
        ShootService.shoot(matrix, "b2");
        ShootService.shoot(matrix, "b3");

        ShootService.shoot(matrix, "d2");
        ShootService.shoot(matrix, "d3");
        ShootService.shoot(matrix, "d4");

        ShootService.shoot(matrix, "h4");
        ShootService.shoot(matrix, "i4");
        ShootService.shoot(matrix, "j4");

        ShootService.shoot(matrix, "d9");
        ShootService.shoot(matrix, "d10");

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
        assertEquals(result, ShootService.shoot(matrix, "b1"));
        assertEquals(result, ShootService.shoot(matrix, "a10"));
        assertEquals(result, ShootService.shoot(matrix, "g5"));

    }

    @Test
    void shouldGetInfoIfShotHits() {

        //given
        Matrix matrix = spy(Matrix.class);

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
        assertEquals("P.1,1", ShootService.shoot(matrix, "b2"));
        assertEquals("D.3,2", ShootService.shoot(matrix, "d3"));
        assertEquals("B.8,8", ShootService.shoot(matrix, "i9"));
        assertEquals("S.9,3", ShootService.shoot(matrix, "j4"));

    }

    @Test
    void shouldProperlyMarkInMatrixAfterShot() {

        //given
        Matrix matrix = new Matrix();

        String[][] result = {
                {".", ".", ".", "!", "!", " ", " ", " ", " ", " ",},
                {".", "X", ".", "D", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", "X", "!", " ", "!", "!", ".", "!",},
                {".", ".", ".", "D", ".", " ", "!", "S", "S", "X",},
                {"!", ".", "!", "!", "!", "!", "!", ".", ".", ".",},
                {"X", "C", "C", "C", "C", "!", " ", ".", "X", ".",},
                {"!", ".", "!", "!", "!", "!", " ", ".", "X", ".",},
                {" ", " ", "!", "!", "!", "!", "!", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "P", "P", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "!", "!", ".", ".", ".",}
        };

        String[][] resultShoot_d6 = {
                {".", ".", ".", "!", "!", " ", " ", " ", " ", " ",},
                {".", "X", ".", "D", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", "X", "!", " ", "!", "!", ".", "!",},
                {".", ".", ".", "D", ".", " ", "!", "S", "S", "X",},
                {"!", ".", ".", "!", ".", "!", "!", ".", ".", ".",},
                {"X", "C", "C", "X", "C", "!", " ", ".", "X", ".",},
                {"!", ".", ".", "!", ".", "!", " ", ".", "X", ".",},
                {" ", " ", "!", "!", "!", "!", "!", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "P", "P", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "!", "!", ".", ".", ".",}
        };

        String[][] resultShoot_e6 = {
                {".", ".", ".", "!", "!", " ", " ", " ", " ", " ",},
                {".", "X", ".", "D", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", "X", "!", " ", "!", "!", ".", "!",},
                {".", ".", ".", "D", ".", " ", "!", "S", "S", "X",},
                {"!", ".", ".", ".", ".", ".", "!", ".", ".", ".",},
                {"X", "C", "C", "X", "X", "!", " ", ".", "X", ".",},
                {"!", ".", ".", ".", ".", ".", " ", ".", "X", ".",},
                {" ", " ", "!", "!", "!", "!", "!", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "P", "P", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "!", "!", ".", ".", ".",}
        };

        String[][] resultShoot_b6 = {
                {".", ".", ".", "!", "!", " ", " ", " ", " ", " ",},
                {".", "X", ".", "D", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", "X", "!", " ", "!", "!", ".", "!",},
                {".", ".", ".", "D", ".", " ", "!", "S", "S", "X",},
                {".", ".", ".", ".", ".", ".", "!", ".", ".", ".",},
                {"X", "X", "C", "X", "X", "!", " ", ".", "X", ".",},
                {".", ".", ".", ".", ".", ".", " ", ".", "X", ".",},
                {" ", " ", "!", "!", "!", "!", "!", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "P", "P", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "!", "!", ".", ".", ".",}
        };

        String[][] resultShoot_c6 = {
                {".", ".", ".", "!", "!", " ", " ", " ", " ", " ",},
                {".", "X", ".", "D", ".", " ", " ", " ", " ", " ",},
                {".", "X", ".", "X", "!", " ", "!", "!", ".", "!",},
                {".", ".", ".", "D", ".", " ", "!", "S", "S", "X",},
                {".", ".", ".", ".", ".", ".", "!", ".", ".", ".",},
                {"X", "X", "X", "X", "X", ".", " ", ".", "X", ".",},
                {".", ".", ".", ".", ".", ".", " ", ".", "X", ".",},
                {" ", " ", "!", "!", "!", "!", "!", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "P", "P", ".", "X", ".",},
                {" ", " ", "!", "P", "!", "!", "!", ".", ".", ".",}
        };

        List<String> carrierEncoded = List.of("C.0,5", "C.1,5", "C.2,5", "C.3,5", "C.4,5");
        List<String> battleshipEncoded = List.of("B.8,8", "B.8,7", "B.8,6", "B.8,5");
        List<String> submarineEncoded = List.of("S.7,3", "S.8,3", "S.9,3");
        List<String> destroyerEncoded = List.of("D.3,1", "D.3,2", "D.3,3");
        List<String> patrolBoat1Encoded = List.of("P.1,1", "P.1,2");
        List<String> patrolBoat2Encoded = List.of("P.3,8", "P.3,9");
        List<String> patrolBoat3Encoded = List.of("P.6,8", "P.5,8");

        FleetService.addEncodedShipToMatrix(carrierEncoded, matrix);
        FleetService.addEncodedShipToMatrix(battleshipEncoded, matrix);
        FleetService.addEncodedShipToMatrix(submarineEncoded, matrix);
        FleetService.addEncodedShipToMatrix(destroyerEncoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat1Encoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat2Encoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat3Encoded, matrix);

        //when
        //then
        ShootService.shoot(matrix, "b2");
        ShootService.shoot(matrix, "b3");
        ShootService.shoot(matrix, "d3");
        ShootService.shoot(matrix, "i9");
        ShootService.shoot(matrix, "i7");
        ShootService.shoot(matrix, "i6");
        ShootService.shoot(matrix, "i8");
        ShootService.shoot(matrix, "j4");
        ShootService.shoot(matrix, "a6");
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
        ShootService.shoot(matrix, "d6");
        assertEquals(Arrays.deepToString(resultShoot_d6), Arrays.deepToString(matrix.getMatrix()));
        ShootService.shoot(matrix, "e6");
        assertEquals(Arrays.deepToString(resultShoot_e6), Arrays.deepToString(matrix.getMatrix()));
        ShootService.shoot(matrix, "b6");
        assertEquals(Arrays.deepToString(resultShoot_b6), Arrays.deepToString(matrix.getMatrix()));
        ShootService.shoot(matrix, "c6");
        assertEquals(Arrays.deepToString(resultShoot_c6), Arrays.deepToString(matrix.getMatrix()));

    }

}