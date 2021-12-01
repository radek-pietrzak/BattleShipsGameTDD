package org.ships.service;

import org.junit.jupiter.api.Test;
import org.ships.service.ValidationService;

import static org.junit.jupiter.api.Assertions.*;

class ValidationServiceTest {

    @Test
    void shouldReturnTrueIfCoordinatesAreValid() {

        assertTrue(ValidationService.areCoordinatesValid("a1"));
        assertTrue(ValidationService.areCoordinatesValid("j10"));
        assertTrue(ValidationService.areCoordinatesValid("j1"));
        assertTrue(ValidationService.areCoordinatesValid("a10"));
        assertTrue(ValidationService.areCoordinatesValid("g2"));
        assertTrue(ValidationService.areCoordinatesValid("h8"));
        assertTrue(ValidationService.areCoordinatesValid("f4"));
        assertTrue(ValidationService.areCoordinatesValid("c3"));
        assertTrue(ValidationService.areCoordinatesValid("d7"));
        assertTrue(ValidationService.areCoordinatesValid("e6"));
    }

    @Test
    void shouldReturnFalseIfCoordinatesAreNotValid() {

        assertFalse(ValidationService.areCoordinatesValid("x5"));
        assertFalse(ValidationService.areCoordinatesValid("10"));
        assertFalse(ValidationService.areCoordinatesValid("abc"));
        assertFalse(ValidationService.areCoordinatesValid(""));
        assertFalse(ValidationService.areCoordinatesValid("6"));
        assertFalse(ValidationService.areCoordinatesValid("92"));
        assertFalse(ValidationService.areCoordinatesValid("110"));
        assertFalse(ValidationService.areCoordinatesValid("b11"));
        assertFalse(ValidationService.areCoordinatesValid("b111"));
    }

    @Test
    void shouldReturnTrueIfShipSetIsValid() {

        assertTrue(ValidationService.isShipSetValid("a1 n"));
        assertTrue(ValidationService.isShipSetValid("g5 w"));
        assertTrue(ValidationService.isShipSetValid("a10 e"));
        assertTrue(ValidationService.isShipSetValid("c3 s"));
        assertTrue(ValidationService.isShipSetValid("j10 w"));
    }

    @Test
    void shouldReturnFalseIfShipSetIsNotValid() {

        assertFalse(ValidationService.isShipSetValid("a n"));
        assertFalse(ValidationService.isShipSetValid("g5 x"));
        assertFalse(ValidationService.isShipSetValid("a10  e"));
        assertFalse(ValidationService.isShipSetValid("c 3 s"));
        assertFalse(ValidationService.isShipSetValid("j10w"));
        assertFalse(ValidationService.isShipSetValid("j10,w"));
    }
}