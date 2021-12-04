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
        fleet.addShipsToFleet();

        //then
        assertThat(fleet.getFleetToSet(), hasSize(7));
        assertThat(fleet.getFleetToSet().get(0), equalTo(Ship.CARRIER));
        assertThat(fleet.getFleetToSet().get(1), equalTo(Ship.BATTLESHIP));
        assertThat(fleet.getFleetToSet().get(2), equalTo(Ship.DESTROYER));
        assertThat(fleet.getFleetToSet().get(3), equalTo(Ship.SUBMARINE));
        assertThat(fleet.getFleetToSet().get(4), equalTo(Ship.PATROL_BOAT));
        assertThat(fleet.getFleetToSet().get(5), equalTo(Ship.PATROL_BOAT));
        assertThat(fleet.getFleetToSet().get(6), equalTo(Ship.PATROL_BOAT));
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
        fleet.addShipsToFleet();
        fleet.removeFirstShipFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleetToSet());
        assertThat(fleet.getFleetToSet().get(0), equalTo(Ship.BATTLESHIP));
    }

    @Test
    void shouldRemoveAllShipTypesFromFleet() {

        //given
        List<Ship> expectedFleet = Collections.emptyList();

        //when
        fleet.addShipsToFleet();
        for (int i = 0; i < 7; i++)
            fleet.removeFirstShipFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleetToSet());
    }

}