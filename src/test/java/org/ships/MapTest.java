package org.ships;

import org.junit.jupiter.api.Test;
import org.ships.service.ShipService;
import org.ships.ship.Ship;
import org.ships.ship.ShipType;
import org.ships.ship.Vector;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    private final Map map = new Map();

    @Test
    void shouldDrawEmptyMaps() {

        //given
        Matrix matrix = new Matrix();

        String result = """
                   A|B|C|D|E|F|G|H|I|J|     A|B|C|D|E|F|G|H|I|J|\r
                1 | | | | | | | | | | |  1 | | | | | | | | | | |\r
                2 | | | | | | | | | | |  2 | | | | | | | | | | |\r
                3 | | | | | | | | | | |  3 | | | | | | | | | | |\r
                4 | | | | | | | | | | |  4 | | | | | | | | | | |\r
                5 | | | | | | | | | | |  5 | | | | | | | | | | |\r
                6 | | | | | | | | | | |  6 | | | | | | | | | | |\r
                7 | | | | | | | | | | |  7 | | | | | | | | | | |\r
                8 | | | | | | | | | | |  8 | | | | | | | | | | |\r
                9 | | | | | | | | | | |  9 | | | | | | | | | | |\r
                10| | | | | | | | | | |  10| | | | | | | | | | |\r
                """;
        //when
        //then
        assertEquals(result, map.drawMaps(matrix, matrix));
    }

    @Test
    void shouldDrawMapsAccordingToMatrix() {

        //given
        ShipService shipService = new ShipService();
        Matrix matrixAlly = new Matrix();
        Matrix matrixEnemy = new Matrix();

        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

        String result = """
                   A|B|C|D|E|F|G|H|I|J|     A|B|C|D|E|F|G|H|I|J|\r
                1 | | | | | | | | | | |  1 | | | | | | | | | | |\r
                2 | |P| |D| | | | | | |  2 | | | | | | | | | | |\r
                3 | |P| |D| | | | | | |  3 | | | | | | | | | | |\r
                4 | | | |D| | | |S|S|S|  4 | | | | | | | | | | |\r
                5 | | | | | | | | | | |  5 | | | | | | | | | | |\r
                6 |C|C|C|C|C| | | |B| |  6 | | | | | | | | | | |\r
                7 | | | | | | | | |B| |  7 | | | | | | | | | | |\r
                8 | | | | | | | | |B| |  8 | | | | | | | | | | |\r
                9 | | | |P| |P|P| |B| |  9 | | | | | | | | | | |\r
                10| | | |P| | | | | | |  10| | | | | | | | | | |\r
                """;

        //when
        shipService.addShipToMatrix(carrier, matrixAlly, "a6");
        shipService.addShipToMatrix(battleship, matrixAlly,  "i9");
        shipService.addShipToMatrix(submarine, matrixAlly,  "j4");
        shipService.addShipToMatrix(destroyer, matrixAlly,  "d2");
        shipService.addShipToMatrix(patrolBoat1, matrixAlly,  "b2");
        shipService.addShipToMatrix(patrolBoat2, matrixAlly,  "d10");
        shipService.addShipToMatrix(patrolBoat3, matrixAlly,  "f9");

        //then
        assertEquals(result, map.drawMaps(matrixAlly, matrixEnemy));
    }

}