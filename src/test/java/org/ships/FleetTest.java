package org.ships;

import org.junit.jupiter.api.Test;
import org.ships.ship.ShipType;

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
        assertThat(fleet.getFleet().get(0), equalTo(ShipType.CARRIER));
        assertThat(fleet.getFleet().get(1), equalTo(ShipType.BATTLESHIP));
        assertThat(fleet.getFleet().get(2), equalTo(ShipType.DESTROYER));
        assertThat(fleet.getFleet().get(3), equalTo(ShipType.SUBMARINE));
        assertThat(fleet.getFleet().get(4), equalTo(ShipType.PATROL_BOAT));
        assertThat(fleet.getFleet().get(5), equalTo(ShipType.PATROL_BOAT));
        assertThat(fleet.getFleet().get(6), equalTo(ShipType.PATROL_BOAT));
    }

    @Test
    void shouldRemoveFirstShipTypeFromFleet() {

        //given
        List<ShipType> expectedFleet = List.of(
                ShipType.BATTLESHIP,
                ShipType.DESTROYER,
                ShipType.SUBMARINE,
                ShipType.PATROL_BOAT,
                ShipType.PATROL_BOAT,
                ShipType.PATROL_BOAT
        );

        //when
        fleet.addShipTypesToFleet();
        fleet.removeFirstShipTypeFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleet());
        assertThat(fleet.getFleet().get(0), equalTo(ShipType.BATTLESHIP));
    }

    @Test
    void shouldRemoveAllShipTypesFromFleet() {

        //given
        List<ShipType> expectedFleet = Collections.emptyList();

        //when
        fleet.addShipTypesToFleet();
        for (int i = 0; i < 7; i++)
            fleet.removeFirstShipTypeFromFleet();

        //then
        assertEquals(expectedFleet, fleet.getFleet());
    }

}