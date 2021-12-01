package org.ships.service;

import org.junit.jupiter.api.Test;
import org.ships.service.CoordinatesService;

import static org.junit.jupiter.api.Assertions.*;

class CoordinatesServiceTest {

    @Test
    void shouldGetProperXFromCoordinates() {

        assertEquals(0, CoordinatesService.getXFromCoordinates("a1"));
        assertEquals(7, CoordinatesService.getXFromCoordinates("h10"));
        assertEquals(9, CoordinatesService.getXFromCoordinates("j10"));
        assertEquals(9, CoordinatesService.getXFromCoordinates("j1"));
        assertEquals(6, CoordinatesService.getXFromCoordinates("g4"));
    }

    @Test
    void shouldGetProperYFromCoordinates() {

        assertEquals(0, CoordinatesService.getYFromCoordinates("a1"));
        assertEquals(9, CoordinatesService.getYFromCoordinates("h10"));
        assertEquals(8, CoordinatesService.getYFromCoordinates("j9"));
        assertEquals(0, CoordinatesService.getYFromCoordinates("j1"));
        assertEquals(9, CoordinatesService.getYFromCoordinates("g10"));
    }

    @Test
    void shouldGetProperCoordinatesFromShipSet(){

        assertEquals("a1", CoordinatesService.getCoordinatesFromShipSet("a1 n"));
        assertEquals("h10", CoordinatesService.getCoordinatesFromShipSet("h10 w"));
        assertEquals("a10", CoordinatesService.getCoordinatesFromShipSet("a10 n"));
        assertEquals("g5", CoordinatesService.getCoordinatesFromShipSet("g5 e"));
        assertEquals("e2", CoordinatesService.getCoordinatesFromShipSet("e2 s"));
    }
}