package org.ships;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class MatrixTest {

    private final Matrix matrix = new Matrix();

    @Test
    void shouldPutShipsToMatrix() {

        //given
        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

        String[][] result = {
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

        //when
        matrix.addShipToMatrix(carrier, "a6");
        matrix.addShipToMatrix(battleship, "i9");
        matrix.addShipToMatrix(submarine, "j4");
        matrix.addShipToMatrix(destroyer, "d2");
        matrix.addShipToMatrix(patrolBoat1, "b2");
        matrix.addShipToMatrix(patrolBoat2, "d10");
        matrix.addShipToMatrix(patrolBoat3, "f9");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

    @TestFactory
    Collection<DynamicTest> shouldReturnInfoOfShipOutOfBounds() {

        //given
        String result = "Ship out of bounds.";

        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        //when
        //then
        Executable execCarrier = () -> assertEquals(result, matrix.addShipToMatrix(carrier, "g3"));
        Executable execBattleship = () -> assertEquals(result, matrix.addShipToMatrix(battleship, "a3"));
        Executable execSubmarine = () -> assertEquals(result, matrix.addShipToMatrix(submarine, "b5"));
        Executable execDestroyer = () -> assertEquals(result, matrix.addShipToMatrix(destroyer, "h10"));
        Executable execPatrol1 = () -> assertEquals(result, matrix.addShipToMatrix(patrolBoat1, "a10"));
        Executable execPatrol2 = () -> assertEquals(result, matrix.addShipToMatrix(patrolBoat2, "c1"));
        Executable execPatrol3 = () -> assertEquals(result, matrix.addShipToMatrix(patrolBoat3, "j5"));

        DynamicTest testCarrier = DynamicTest.dynamicTest("carrier", execCarrier);
        DynamicTest testBattleship = DynamicTest.dynamicTest("battleship", execBattleship);
        DynamicTest testSubmarine = DynamicTest.dynamicTest("submarine", execSubmarine);
        DynamicTest testDestroyer = DynamicTest.dynamicTest("destroyer", execDestroyer);
        DynamicTest testPatrol1 = DynamicTest.dynamicTest("patrol1", execPatrol1);
        DynamicTest testPatrol2 = DynamicTest.dynamicTest("patrol2", execPatrol2);
        DynamicTest testPatrol3 = DynamicTest.dynamicTest("patrol3", execPatrol3);

        dynamicTests.add(testCarrier);
        dynamicTests.add(testBattleship);
        dynamicTests.add(testSubmarine);
        dynamicTests.add(testDestroyer);
        dynamicTests.add(testPatrol1);
        dynamicTests.add(testPatrol2);
        dynamicTests.add(testPatrol3);

        return dynamicTests;
    }

    @Test
    void shouldReturnInfoIfGoodPlacementOfShip() {

        //given
        String result = "Ship added.";
        Ship ship = new Ship(ShipType.BATTLESHIP, Vector.EAST);
        String position = "a1";

        //when
        //then
        assertEquals(result, matrix.addShipToMatrix(ship, position));

    }

    @Test
    void shouldGetInfoIfShipIsOnAnother() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Ship on another.";

        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);

        String[][] givenMatrix = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", "C", "C", "C", "C", "C", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}
        };


        given(matrix.getMatrix()).willReturn(givenMatrix);

        //when
        //then
        assertEquals(result, matrix.addShipToMatrix(destroyer, "b1"));
        assertEquals(result, matrix.addShipToMatrix(patrolBoat2, "f3"));
    }

    @Test
    void shouldReturnInfoIfShipIsToCloseToAnother() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Ship to close to another.";

        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

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
        assertEquals(result, matrix.addShipToMatrix(carrier, "a1"));
        assertEquals(result, matrix.addShipToMatrix(battleship, "h9"));
        assertEquals(result, matrix.addShipToMatrix(submarine, "h3"));
        assertEquals(result, matrix.addShipToMatrix(destroyer, "g1"));
        assertEquals(result, matrix.addShipToMatrix(patrolBoat1, "j2"));
        assertEquals(result, matrix.addShipToMatrix(patrolBoat2, "j3"));
        assertEquals(result, matrix.addShipToMatrix(patrolBoat3, "g7"));
    }

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