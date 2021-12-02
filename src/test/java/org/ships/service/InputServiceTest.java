package org.ships.service;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.ships.Ship;

import java.io.ByteArrayInputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputServiceTest {

    @ParameterizedTest
    @MethodSource("createShipsTypesWithInputs")
    void shouldReturnProperlyEncodedShip(Ship ship, String input) {

        //given
        List<String> expectedResult = new LinkedList<>();

        if (ship.equals(Ship.CARRIER))
            expectedResult = List.of("C.0,5", "C.1,5", "C.2,5", "C.3,5", "C.4,5");

        if (ship.equals(Ship.BATTLESHIP))
            expectedResult = List.of("B.8,8", "B.8,7", "B.8,6", "B.8,5");

        if (ship.equals(Ship.SUBMARINE))
            expectedResult = List.of("S.9,3", "S.8,3", "S.7,3");

        if (ship.equals(Ship.DESTROYER))
            expectedResult = List.of("D.3,1", "D.3,2", "D.3,3");

        if (ship.equals(Ship.PATROL_BOAT))
            expectedResult = List.of("P.1,1", "P.1,2");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);

        //when
        List<String> result = InputService.createEncodedShipFromInput(ship);

        //then
        assertEquals(expectedResult, result);
    }

    public static Stream<Arguments> createShipsTypesWithInputs() {

        return Stream.of(
                Arguments.of(Ship.CARRIER, "a6 e"),
                Arguments.of(Ship.BATTLESHIP, "i9 n"),
                Arguments.of(Ship.SUBMARINE, "j4 w"),
                Arguments.of(Ship.DESTROYER, "d2 s"),
                Arguments.of(Ship.PATROL_BOAT, "b2 s")
                );
    }

}