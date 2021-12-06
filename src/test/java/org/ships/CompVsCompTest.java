package org.ships;

import org.junit.jupiter.api.Test;


import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.spy;

public class CompVsCompTest {


    @Test
    void totallyRandomVsTotallyRandomRatio() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);

            game.startGame();

            if (game.getEnemyFleet().getEncodedFleet().isEmpty())
                countAllyWins += 1;
            if (game.getAllyFleet().getEncodedFleet().isEmpty())
                countEnemyWins += 1;

        }

        //then
        System.out.println("Ally wins: " + countAllyWins);
        System.out.println("Enemy wins: " + countEnemyWins);

    }
}
