package org.ships.service;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.ships.Matrix;
import org.ships.ship.Ship;
import org.ships.ship.ShipType;
import org.ships.ship.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class ShipServiceTest {

    private final ShipService shipService = new ShipService();
    private final Matrix matrix = new Matrix();

    @Test
    void shouldAddShipsToMatrix() {

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
        shipService.addShipToMatrix(carrier, matrix, "a6");
        shipService.addShipToMatrix(battleship, matrix, "i9");
        shipService.addShipToMatrix(submarine, matrix, "j4");
        shipService.addShipToMatrix(destroyer, matrix, "d2");
        shipService.addShipToMatrix(patrolBoat1, matrix, "b2");
        shipService.addShipToMatrix(patrolBoat2, matrix, "d10");
        shipService.addShipToMatrix(patrolBoat3, matrix, "f9");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

    @TestFactory
    Collection<DynamicTest> shouldReturnShipOutOfBounds() {

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
        Executable execCarrier = () -> assertEquals(result, shipService.addShipToMatrix(carrier, matrix, "g3"));
        Executable execBattleship = () -> assertEquals(result, shipService.addShipToMatrix(battleship, matrix, "a3"));
        Executable execSubmarine = () -> assertEquals(result, shipService.addShipToMatrix(submarine, matrix, "b5"));
        Executable execDestroyer = () -> assertEquals(result, shipService.addShipToMatrix(destroyer, matrix, "h10"));
        Executable execPatrol1 = () -> assertEquals(result, shipService.addShipToMatrix(patrolBoat1, matrix, "a10"));
        Executable execPatrol2 = () -> assertEquals(result, shipService.addShipToMatrix(patrolBoat2, matrix, "c1"));
        Executable execPatrol3 = () -> assertEquals(result, shipService.addShipToMatrix(patrolBoat3, matrix, "j5"));

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
    void shouldReturnShipOnAnother() {

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
        assertEquals(result, shipService.addShipToMatrix(destroyer, matrix, "b1"));
        assertEquals(result, shipService.addShipToMatrix(patrolBoat2, matrix, "f3"));
    }

    @Test
    void shouldReturnShipTooCloseToAnother() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Ship too close to another.";

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
        assertEquals(result, shipService.addShipToMatrix(carrier, matrix, "a1"));
        assertEquals(result, shipService.addShipToMatrix(battleship, matrix, "h9"));
        assertEquals(result, shipService.addShipToMatrix(submarine, matrix, "h3"));
        assertEquals(result, shipService.addShipToMatrix(destroyer, matrix, "g1"));
        assertEquals(result, shipService.addShipToMatrix(patrolBoat1, matrix, "j2"));
        assertEquals(result, shipService.addShipToMatrix(patrolBoat2, matrix, "j3"));
        assertEquals(result, shipService.addShipToMatrix(patrolBoat3, matrix, "g7"));
    }

    @Test
    void shouldReturnShipAdded() {

        //given
        String result = "Ship added.";
        Ship ship = new Ship(ShipType.BATTLESHIP, Vector.EAST);
        String position = "a1";

        //when
        //then
        assertEquals(result, shipService.addShipToMatrix(ship, matrix, position));
    }

}