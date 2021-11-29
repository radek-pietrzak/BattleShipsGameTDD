package org.ships;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {

    @Test
    void shouldEncodeShipProperlyToCoordinates() {

        //given
        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

        List<String> carrierEncoded = List.of("C.0,5","C.1,5","C.2,5","C.3,5","C.4,5");
        List<String> battleshipEncoded = List.of("B.8,8","B.8,7","B.8,6","B.8,5");
        List<String> submarineEncoded = List.of("S.9,3","S.8,3","S.7,3");
        List<String> destroyerEncoded = List.of("D.3,1","D.3,2","D.3,3");
        List<String> patrolBoat1Encoded = List.of("P.1,1","P.1,2");
        List<String> patrolBoat2Encoded = List.of("P.3,9","P.3,8");
        List<String> patrolBoat3Encoded = List.of("P.5,8","P.6,8");



        //when
        //then
        assertEquals(carrierEncoded, Ship.encodeShipToCoordinates(carrier, "a6"));
        assertEquals(battleshipEncoded, Ship.encodeShipToCoordinates(battleship, "i9"));
        assertEquals(submarineEncoded, Ship.encodeShipToCoordinates(submarine, "j4"));
        assertEquals(destroyerEncoded, Ship.encodeShipToCoordinates(destroyer, "d2"));
        assertEquals(patrolBoat1Encoded, Ship.encodeShipToCoordinates(patrolBoat1, "b2"));
        assertEquals(patrolBoat2Encoded, Ship.encodeShipToCoordinates(patrolBoat2, "d10"));
        assertEquals(patrolBoat3Encoded, Ship.encodeShipToCoordinates(patrolBoat3, "f9"));
    }
}