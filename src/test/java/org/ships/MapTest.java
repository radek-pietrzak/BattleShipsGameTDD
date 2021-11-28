package org.ships;

import org.junit.jupiter.api.Test;

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
    void shouldDrawMapAccordingToMatrix() {

        //given
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
        matrixAlly.addShipToMatrix(carrier, "a6");
        matrixAlly.addShipToMatrix(battleship, "i9");
        matrixAlly.addShipToMatrix(submarine, "j4");
        matrixAlly.addShipToMatrix(destroyer, "d2");
        matrixAlly.addShipToMatrix(patrolBoat1, "b2");
        matrixAlly.addShipToMatrix(patrolBoat2, "d10");
        matrixAlly.addShipToMatrix(patrolBoat3, "f9");

        //then
        assertEquals(result, map.drawMaps(matrixAlly, matrixEnemy));
    }

}