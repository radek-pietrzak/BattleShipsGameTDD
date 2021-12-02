package org.ships.service;

import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;
import org.ships.Matrix;
import org.ships.Ship;
import org.ships.Vector;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class FleetServiceTest {

    private final Matrix matrix = new Matrix();

    @Test
    void shouldAddShipsToMatrix() {

        //given
        List<String> carrierEncoded = List.of("C.0,5", "C.1,5", "C.2,5", "C.3,5", "C.4,5");
        List<String> battleshipEncoded = List.of("B.8,8", "B.8,7", "B.8,6", "B.8,5");
        List<String> submarineEncoded = List.of("S.9,3", "S.8,3", "S.7,3");
        List<String> destroyerEncoded = List.of("D.3,1", "D.3,2", "D.3,3");
        List<String> patrolBoat1Encoded = List.of("P.1,1", "P.1,2");
        List<String> patrolBoat2Encoded = List.of("P.3,9", "P.3,8");
        List<String> patrolBoat3Encoded = List.of("P.5,8", "P.6,8");

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
        FleetService.addEncodedShipToMatrix(carrierEncoded, matrix);
        FleetService.addEncodedShipToMatrix(battleshipEncoded, matrix);
        FleetService.addEncodedShipToMatrix(submarineEncoded, matrix);
        FleetService.addEncodedShipToMatrix(destroyerEncoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat1Encoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat2Encoded, matrix);
        FleetService.addEncodedShipToMatrix(patrolBoat3Encoded, matrix);

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

    @TestFactory
    Collection<DynamicTest> shouldReturnShipOutOfBounds() {

        //given
        String result = "Ship out of bounds.ERROR";

        List<String> carrierEncoded = List.of("C.-1,5", "C.0,5", "C.1,5", "C.2,5", "C.3,5");
        List<String> battleshipEncoded = List.of("B.8,8", "B.8,9", "B.8,10", "B.8,11");
        List<String> submarineEncoded = List.of("S.10,3", "S.9,3", "S.8,3");
        List<String> destroyerEncoded = List.of("D.3,-1", "D.3,0", "D.3,1");
        List<String> patrolBoat1Encoded = List.of("P.-1,-1", "P.0,-1");
        List<String> patrolBoat2Encoded = List.of("P.-11,9", "P.-12,8");
        List<String> patrolBoat3Encoded = List.of("P.0,0", "P.0,-1");

        Collection<DynamicTest> dynamicTests = new ArrayList<>();

        //when
        //then
        Executable execCarrier = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(carrierEncoded, matrix));
        Executable execBattleship = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(battleshipEncoded, matrix));
        Executable execSubmarine = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(submarineEncoded, matrix));
        Executable execDestroyer = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(destroyerEncoded, matrix));
        Executable execPatrol1 = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat1Encoded, matrix));
        Executable execPatrol2 = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat2Encoded, matrix));
        Executable execPatrol3 = () -> assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat3Encoded, matrix));

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
        String result = "Ship on another.ERROR";

        List<String> destroyerEncoded = List.of("D.1,0", "D.1,1", "D.1,2");
        List<String> patrolBoatEncoded = List.of("P.5,2", "P.5,1");

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
        assertEquals(result, FleetService.addEncodedShipToMatrix(destroyerEncoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoatEncoded, matrix));
    }

    @Test
    void shouldReturnShipTooCloseToAnother() {

        //given
        Matrix matrix = spy(Matrix.class);
        String result = "Ship too close to another.ERROR";

        List<String> carrierEncoded = List.of("C.0,0", "C.1,0", "C.2,0", "C.3,0", "C.4,0");
        List<String> battleshipEncoded = List.of("B.7,8", "B.7,7", "B.7,6", "B.7,5");
        List<String> submarineEncoded = List.of("S.7,6", "S.6,6", "S.5,6");
        List<String> destroyerEncoded = List.of("D.6,0", "D.6,1", "D.6,2");
        List<String> patrolBoat1Encoded = List.of("P.9,1", "P.9,2");
        List<String> patrolBoat2Encoded = List.of("P.9,2", "P.9,1");
        List<String> patrolBoat3Encoded = List.of("P.6,6", "P.5,6");

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
        assertEquals(result, FleetService.addEncodedShipToMatrix(carrierEncoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(battleshipEncoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(submarineEncoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(destroyerEncoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat1Encoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat2Encoded, matrix));
        assertEquals(result, FleetService.addEncodedShipToMatrix(patrolBoat3Encoded, matrix));
    }

    @Test
    void shouldReturnShipAdded() {

        //given
        String result = "Ship added.";

        List<String> battleshipEncoded = List.of("B.0,0", "B.1,0", "B.2,0", "B.3,0");

        //when
        //then
        assertEquals(result, FleetService.addEncodedShipToMatrix(battleshipEncoded, matrix));
    }

    @Test
    void shouldEncodeShipWithCoordinatesAndVectorProperly() {

        //given
        List<String> carrierEncoded = List.of("C.0,5","C.1,5","C.2,5","C.3,5","C.4,5");
        List<String> battleshipEncoded = List.of("B.8,8","B.8,7","B.8,6","B.8,5");
        List<String> submarineEncoded = List.of("S.9,3","S.8,3","S.7,3");
        List<String> destroyerEncoded = List.of("D.3,1","D.3,2","D.3,3");
        List<String> patrolBoat1Encoded = List.of("P.1,1","P.1,2");
        List<String> patrolBoat2Encoded = List.of("P.3,9","P.3,8");
        List<String> patrolBoat3Encoded = List.of("P.5,8","P.6,8");

        //when
        //then
        assertEquals(carrierEncoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.CARRIER, Vector.EAST, "a6"));
        assertEquals(battleshipEncoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.BATTLESHIP, Vector.NORTH, "i9"));
        assertEquals(submarineEncoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.SUBMARINE, Vector.WEST, "j4"));
        assertEquals(destroyerEncoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.DESTROYER, Vector.SOUTH, "d2"));
        assertEquals(patrolBoat1Encoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.PATROL_BOAT, Vector.SOUTH,"b2"));
        assertEquals(patrolBoat2Encoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.PATROL_BOAT,Vector.NORTH, "d10"));
        assertEquals(patrolBoat3Encoded, FleetService.encodeShipWithCoordinatesAndVector(Ship.PATROL_BOAT, Vector.EAST,"f9"));
    }

}