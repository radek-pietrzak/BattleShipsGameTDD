package org.ships.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.ships.ship.Ship;
import org.ships.ship.ShipType;

import java.io.ByteArrayInputStream;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InputServiceTest {

    @ParameterizedTest
    @MethodSource("createShipsTypesWithInputs")
    void shouldReturnProperEncodedShip(ShipType shipType, String input) {

        //given
        List<String> expectedResult = new LinkedList<>();

        if (shipType.equals(ShipType.CARRIER))
            expectedResult = List.of("C.0,5", "C.1,5", "C.2,5", "C.3,5", "C.4,5");

        if (shipType.equals(ShipType.BATTLESHIP))
            expectedResult = List.of("B.8,8", "B.8,7", "B.8,6", "B.8,5");

        if (shipType.equals(ShipType.SUBMARINE))
            expectedResult = List.of("S.9,3", "S.8,3", "S.7,3");

        if (shipType.equals(ShipType.DESTROYER))
            expectedResult = List.of("D.3,1", "D.3,2", "D.3,3");

        if (shipType.equals(ShipType.PATROL_BOAT))
            expectedResult = List.of("P.1,1", "P.1,2");

        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(byteArrayInputStream);

        //when
        List<String> result = InputService.createEncodedShipFromInput(shipType);

        //then
        assertEquals(expectedResult, result);
    }

    public static Stream<Arguments> createShipsTypesWithInputs() {

        return Stream.of(
                Arguments.of(ShipType.CARRIER, "a6 e"),
                Arguments.of(ShipType.BATTLESHIP, "i9 n"),
                Arguments.of(ShipType.SUBMARINE, "j4 w"),
                Arguments.of(ShipType.DESTROYER, "d2 s"),
                Arguments.of(ShipType.PATROL_BOAT, "b2 s")
                );
    }

}