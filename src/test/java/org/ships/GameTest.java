package org.ships;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.ships.ship.ShipType;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final Game game = new Game();

    @Disabled
    @Test
    void shouldAddAllShipTypesToList() {

        //given
        List<ShipType> result = new ArrayList<>();
        result.add(ShipType.CARRIER);
        result.add(ShipType.BATTLESHIP);
        result.add(ShipType.DESTROYER);
        result.add(ShipType.SUBMARINE);
        result.add(ShipType.PATROL_BOAT);
        result.add(ShipType.PATROL_BOAT);
        result.add(ShipType.PATROL_BOAT);

        //when
        game.startGame();

        //then
        assertEquals(result.toString(), game.getShipTypes().toString());

    }

}