package org.ships;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.spy;

class MatrixTest {

    @Test
    void shouldCopyAllShotsToAnotherMatrix(){

        //given
        Matrix matrix = new Matrix();
        Matrix givenMatrix = spy(Matrix.class);

        String[][] givenM = {
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

        given(givenMatrix.getMatrix()).willReturn(givenM);


        String[][] result = {
                {" ", " ", " ", " ", " ", ".", " ", " ", " ", " ",},
                {" ", " ", " ", "X", " ", ".", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", "X", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
                {" ", ".", " ", " ", " ", " ", " ", " ", " ", " ",}
        };

        //when
        Matrix.copyAllShots(givenMatrix, matrix);

        //then
        assertThat(matrix.getMatrix(), equalTo(result));
    }

}