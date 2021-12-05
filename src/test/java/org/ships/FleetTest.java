package org.ships;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;

class FleetTest {

    private final Fleet fleet = new Fleet();

    @Test
    void shouldAddAllShipTypesToFleet() {

        //when
        fleet.addShipsToFleetToSet();

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
        fleet.addShipsToFleetToSet();
        fleet.removeFirstShipFromFleetToSet();

        //then
        assertEquals(expectedFleet, fleet.getFleetToSet());
        assertThat(fleet.getFleetToSet().get(0), equalTo(Ship.BATTLESHIP));
    }

    @Test
    void shouldRemoveAllShipTypesFromFleet() {

        //given
        List<Ship> expectedFleet = Collections.emptyList();

        //when
        fleet.addShipsToFleetToSet();
        for (int i = 0; i < 7; i++)
            fleet.removeFirstShipFromFleetToSet();

        //then
        assertEquals(expectedFleet, fleet.getFleetToSet());
    }

    @Test
    void shouldAddEncodedShipToFleet(){

        //given
        List<String> carrierEncoded = new LinkedList<>();
        carrierEncoded.add("C.0,5");
        carrierEncoded.add("C.1,5");
        carrierEncoded.add("C.2,5");
        carrierEncoded.add("C.3,5");
        carrierEncoded.add("C.4,5");

        List<String> expected = List.of("C.0,5", "C.1,5", "C.2,5", "C.3,5", "C.4,5");

        //when
        fleet.addShipsToEncodedFleet(carrierEncoded);

        //then
        assertThat(fleet.getEncodedFleet(), not(empty()));
        assertThat(fleet.getEncodedFleet(), contains(expected));


    }

    @Test
    void shouldRemoveShipPartAfterHit() {

        //given
        List<String> carrierEncoded = new LinkedList<>();
        carrierEncoded.add("C.0,5");
        carrierEncoded.add("C.1,5");
        carrierEncoded.add("C.2,5");
        carrierEncoded.add("C.3,5");
        carrierEncoded.add("C.4,5");

        List<String> battleshipEncoded = new LinkedList<>();
        battleshipEncoded.add("B.8,8");
        battleshipEncoded.add("B.8,7");
        battleshipEncoded.add("B.8,6");
        battleshipEncoded.add("B.8,5");

        List<String> submarineEncoded = new LinkedList<>();
        submarineEncoded.add("S.9,3");
        submarineEncoded.add("S.8,3");
        submarineEncoded.add("S.7,3");

        List<String> destroyerEncoded = new LinkedList<>();
        destroyerEncoded.add("D.3,1");
        destroyerEncoded.add("D.3,2");
        destroyerEncoded.add("D.3,3");

        List<String> patrolBoat1Encoded = new LinkedList<>();
        patrolBoat1Encoded.add("P.1,1");
        patrolBoat1Encoded.add("P.1,2");

        List<String> patrolBoat2Encoded = new LinkedList<>();
        patrolBoat2Encoded.add("P.3,9");
        patrolBoat2Encoded.add("P.3,8");

        List<String> patrolBoat3Encoded = new LinkedList<>();
        patrolBoat3Encoded.add("P.5,8");
        patrolBoat3Encoded.add("P.6,8");

        fleet.addShipsToEncodedFleet(carrierEncoded);
        fleet.addShipsToEncodedFleet(battleshipEncoded);
        fleet.addShipsToEncodedFleet(submarineEncoded);
        fleet.addShipsToEncodedFleet(destroyerEncoded);
        fleet.addShipsToEncodedFleet(patrolBoat1Encoded);
        fleet.addShipsToEncodedFleet(patrolBoat2Encoded);
        fleet.addShipsToEncodedFleet(patrolBoat3Encoded);

        List<String> expectedCarrier = List.of("C.1,5", "C.2,5", "C.3,5", "C.4,5");
        List<String> expectedBattleship = List.of("B.8,8", "B.8,7", "B.8,6", "B.8,5");
        List<String> expectedSubmarine = List.of("S.9,3", "S.8,3", "S.7,3");
        List<String> expectedDestroyer = List.of("D.3,1", "D.3,3");
        List<String> expectedPatrolBoat1 = List.of("P.1,1", "P.1,2");
        List<String> expectedPatrolBoat2 = List.of("P.3,9", "P.3,8");

        List<List<String>> expected = new LinkedList<>();
        expected.add(expectedCarrier);
        expected.add(expectedBattleship);
        expected.add(expectedSubmarine);
        expected.add(expectedDestroyer);
        expected.add(expectedPatrolBoat1);
        expected.add(expectedPatrolBoat2);

        //when
        fleet.removeShipFromListAfterHit("C.0,5");
        fleet.removeShipFromListAfterHit("P.5,8");
        fleet.removeShipFromListAfterHit("P.6,8");
        fleet.removeShipFromListAfterHit("D.3,2");

        //then
        assertThat(fleet.getEncodedFleet(), equalTo(expected));

    }

}