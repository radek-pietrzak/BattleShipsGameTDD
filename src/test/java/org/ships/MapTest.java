package org.ships;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class MapTest {

    private final Map map = new Map();

    @Test
    void shouldConstructEmptyMaps() {

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
        assertEquals(result, map.constructMaps(matrix, matrix));
    }

    @Test
    void shouldConstructMapsAccordingToMatrix() {

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
        assertEquals(result, map.constructMaps(matrixAlly, matrixEnemy));
    }

    @Test
    void shouldConstructProperlyEnemyMap() {

        //given
        Matrix matrixEnemy = spy(Matrix.class);
        Matrix matrixAlly = new Matrix();

        String result = """
                   A|B|C|D|E|F|G|H|I|J|     A|B|C|D|E|F|G|H|I|J|\r
                1 | | | | | | | | | | |  1 | | | | | |.| | | | |\r
                2 | | | | | | | | | | |  2 | | | |X| |.| | | | |\r
                3 | | | | | | | | | | |  3 | | | | | | | | | | |\r
                4 | | | | | | | | | | |  4 | | | | | | | | | | |\r
                5 | | | | | | | | | | |  5 | | | | | | | | | | |\r
                6 | | | | | | | | | | |  6 | | | | |X| | | | | |\r
                7 | | | | | | | | | | |  7 | | | | | | | | | | |\r
                8 | | | | | | | | | | |  8 | | | | | | | | | | |\r
                9 | | | | | | | | | | |  9 | | | | | | | | | | |\r
                10| | | | | | | | | | |  10| |.| | | | | | | | |\r
                """;

        String[][] givenMatrix = {
                {"!", "!", "!", "!", "!", ".", " ", " ", " ", " ",},
                {"!", "P", "!", "X", "!", ".", " ", " ", " ", " ",},
                {"!", "P", "!", "D", "!", " ", "!", "!", "!", "!",},
                {"!", "!", "!", "D", "!", " ", "!", "S", "S", "S",},
                {"!", "!", "!", "!", "!", "!", "!", "!", "!", "!",},
                {"C", "C", "C", "C", "X", "!", " ", "!", "B", "!",},
                {"!", "!", "!", "!", "!", "!", " ", "!", "B", "!",},
                {" ", " ", "!", "!", "!", "!", "!", "!", "B", "!",},
                {" ", " ", "!", "P", "!", "P", "P", "!", "B", "!",},
                {" ", ".", "!", "P", "!", "!", "!", "!", "!", "!",}
        };

        given(matrixEnemy.getMatrix()).willReturn(givenMatrix);

        //when
        //then
        assertEquals(result, map.constructMaps(matrixAlly, matrixEnemy));
    }
}