package org.ships;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MatrixTest {

    private final Matrix matrix = new Matrix();

    @Test
    void shouldPutShipsToMatrix() {

        //given
        Ship carrier = new Ship(ShipType.CARRIER, Vector.EAST);
        Ship battleship = new Ship(ShipType.BATTLESHIP, Vector.NORTH);
        Ship submarine = new Ship(ShipType.SUBMARINE, Vector.WEST);
        Ship destroyer = new Ship(ShipType.DESTROYER, Vector.SOUTH);
        Ship patrolBoat1 = new Ship(ShipType.PATROL_BOAT, Vector.SOUTH);
        Ship patrolBoat2 = new Ship(ShipType.PATROL_BOAT, Vector.NORTH);
        Ship patrolBoat3 = new Ship(ShipType.PATROL_BOAT, Vector.EAST);

        String[][] result = {
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", "P", " ", "D", " ", " ", " ", " ", " ", " ",},
                {" ", "P", " ", "D", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", "D", " ", " ", " ", "S", "S", "S",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {"C", "C", "C", "C", "C", " ", " ", " ", "B", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", "B", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", "B", " ",},
                {" ", " ", " ", "P", " ", "P", "P", " ", "B", " ",},
                {" ", " ", " ", "P", " ", " ", " ", " ", " ", " ",}
        };

        //when
        matrix.addShipToMatrix(carrier, "a6");
        matrix.addShipToMatrix(battleship, "i9");
        matrix.addShipToMatrix(submarine, "j4");
        matrix.addShipToMatrix(destroyer, "d2");
        matrix.addShipToMatrix(patrolBoat1, "b2");
        matrix.addShipToMatrix(patrolBoat2, "d10");
        matrix.addShipToMatrix(patrolBoat3, "f9");

        //then
        assertEquals(Arrays.deepToString(result), Arrays.deepToString(matrix.getMatrix()));
    }

}