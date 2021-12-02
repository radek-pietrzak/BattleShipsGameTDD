package org.ships.service;

import org.junit.jupiter.api.Test;
import org.ships.Vector;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
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
    void shouldGetProperCoordinatesFromShipSet() {

        assertEquals("a1", CoordinatesService.getCoordinatesFromShipSet("a1 n"));
        assertEquals("h10", CoordinatesService.getCoordinatesFromShipSet("h10 w"));
        assertEquals("a10", CoordinatesService.getCoordinatesFromShipSet("a10 n"));
        assertEquals("g5", CoordinatesService.getCoordinatesFromShipSet("g5 e"));
        assertEquals("e2", CoordinatesService.getCoordinatesFromShipSet("e2 s"));
    }

    @Test
    void shouldGetProperVectorFromShipSet() {

        assertEquals(Vector.NORTH, CoordinatesService.getVectorFromShipSet("a1 n"));
        assertEquals(Vector.WEST, CoordinatesService.getVectorFromShipSet("h10 w"));
        assertEquals(Vector.NORTH, CoordinatesService.getVectorFromShipSet("a10 n"));
        assertEquals(Vector.EAST, CoordinatesService.getVectorFromShipSet("g5 e"));
        assertEquals(Vector.SOUTH, CoordinatesService.getVectorFromShipSet("e2 s"));
    }

    @Test
    void shouldGetCoordinatesFromXY() {

        assertThat(CoordinatesService.getCoordinatesFromXY(0, 0), equalTo("a1"));
        assertThat(CoordinatesService.getCoordinatesFromXY(1, 2), equalTo("b3"));
        assertThat(CoordinatesService.getCoordinatesFromXY(9, 9), equalTo("j10"));
    }
}