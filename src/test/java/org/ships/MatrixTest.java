package org.ships;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

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
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", "P", " ", "D", " ", " ", " ", " ", " ", " ",},
                {" ", "P", " ", "D", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", "D", " ", " ", " ", "S", "S", "S",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {"C", "C", "C", "C", "C", " ", " ", " ", "B", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", "B", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", "B", " ",},
                {" ", " ", " ", "P", " ", "P", "P", " ", "B", " ",},
                {" ", " ", " ", "P", " ", " ", " ", " ", " ", " ",}
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
    Collection<DynamicTest> shouldReturnInfoIfBadPlacementOfShip(){

        //given
        String result = "Bad placement of the ship.";

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
        Executable execCarrier = ()-> assertEquals(result, matrix.addShipToMatrix(carrier, "g3"));
        Executable execBattleship = ()-> assertEquals(result, matrix.addShipToMatrix(battleship, "a3"));
        Executable execSubmarine = ()-> assertEquals(result, matrix.addShipToMatrix(submarine, "b5"));
        Executable execDestroyer = ()-> assertEquals(result, matrix.addShipToMatrix(destroyer, "h10"));
        Executable execPatrol1 = ()-> assertEquals(result, matrix.addShipToMatrix(patrolBoat1, "a10"));
        Executable execPatrol2 = ()-> assertEquals(result, matrix.addShipToMatrix(patrolBoat2, "c1"));
        Executable execPatrol3 = ()-> assertEquals(result, matrix.addShipToMatrix(patrolBoat3, "j5"));

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
    void shouldReturnInfoIfGoodPlacementOfShip(){

        //given
        String result = "Ship added.";
        Ship ship = new Ship(ShipType.BATTLESHIP, Vector.EAST);
        String position = "a1";

        //when
        //then
        assertEquals(result, matrix.addShipToMatrix(ship, position));

    }

}