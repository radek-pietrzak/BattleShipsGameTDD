package org.ships;

import org.junit.jupiter.api.Test;
import org.ships.service.ShipService;
import org.ships.ship.Ship;
import org.ships.ship.ShipType;
import org.ships.ship.Vector;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

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
        Matrix matrixAlly = spy(Matrix.class);
        Matrix matrixEnemy = new Matrix();

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

        String[][] givenMatrix = {
                {"!", "!", "!", "!", "!", " ", " ", " ", " ", " ",},
                {"!", "P", "!", "D", "!", " ", " ", " ", " ", " ",},
                {"!", "P", "!", "D", "!", " ", "!", "!", "!", "!",},
                {"!", "!", "!", "D", "!", " ", "!", "S", "S", "S",},
                {"!", "!", "!", "!", "!", "!", "!", "!", "!", "!",},
                {"C", "C", "C", "C", "C", "!", " ", "!", "B", "!",},
                {"!", "!", "!", "!", "!", "!", " ", "!", "B", "!",},
                {" ", " ", "!", "!", "!", "!", "!", "!", "B", "!",},
                {" ", " ", "!", "P", "!", "P", "P", "!", "B", "!",},
                {" ", " ", "!", "P", "!", "!", "!", "!", "!", "!",}
        };

        given(matrixAlly.getMatrix()).willReturn(givenMatrix);

        //when
        //then
        assertEquals(result, map.drawMaps(matrixAlly, matrixEnemy));
    }

}