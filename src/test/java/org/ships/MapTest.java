package org.ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {

    Map map = new Map();
    Matrix matrix = new Matrix();

    @Test
    void shouldDrawEmptyMap() {

        //given
        String result = """
                   A|B|C|D|E|F|G|H|I|J|\r
                1 | | | | | | | | | | |\r
                2 | | | | | | | | | | |\r
                3 | | | | | | | | | | |\r
                4 | | | | | | | | | | |\r
                5 | | | | | | | | | | |\r
                6 | | | | | | | | | | |\r
                7 | | | | | | | | | | |\r
                8 | | | | | | | | | | |\r
                9 | | | | | | | | | | |\r
                10| | | | | | | | | | |\r
                """;
        //when
        //then
        assertEquals(result, map.drawMap(matrix));
    }

}