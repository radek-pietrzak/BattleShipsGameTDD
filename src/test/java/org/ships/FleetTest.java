package org.ships;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    private final Fleet fleet = new Fleet();

    @Test
    void shouldAddAllShipTypesToFleet() {

        //when
        fleet.addShipTypesToFleet();

        //then
        assertThat(fleet.getFleet(), hasSize(7));
        assertThat(fleet.getFleet().get(0), equalTo(Ship.CARRIER));
        assertThat(fleet.getFleet().get(1), equalTo(Ship.BATTLESHIP));
        assertThat(fleet.getFleet().get(2), equalTo(Ship.DESTROYER));
        assertThat(fleet.getFleet().get(3), equalTo(Ship.SUBMARINE));
        assertThat(fleet.getFleet().get(4), equalTo(Ship.PATROL_BOAT));
        assertThat(fleet.getFleet().get(5), equalTo(Ship.PATROL_BOAT));
        assertThat(fleet.getFleet().get(6), equalTo(Ship.PATROL_BOAT));
    }

    @Test
    void shouldRemoveFirstShipTypeFromFleet() {

        //given
        List<Ship> expectedFleet = List.of(
                Ship.BATTLESHIP,
                Ship.DESTROYER,
                Ship.SUBMARINE,
                Ship.PATROL_BOAT,
                Ship.PATROL_BOAT,
                Ship.PATROL_BOAT
        );

        //when
        fleet.addShipTypesToFleet();
        fleet.removeFirstShipTypeFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleet());
        assertThat(fleet.getFleet().get(0), equalTo(Ship.BATTLESHIP));
    }

    @Test
    void shouldRemoveAllShipTypesFromFleet() {

        //given
        List<Ship> expectedFleet = Collections.emptyList();

        //when
        fleet.addShipTypesToFleet();
        for (int i = 0; i < 7; i++)
            fleet.removeFirstShipTypeFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleet());
    }

}