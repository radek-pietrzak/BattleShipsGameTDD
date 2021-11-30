package org.ships;

import org.junit.jupiter.api.Test;

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
}