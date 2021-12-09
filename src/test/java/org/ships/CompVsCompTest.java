package org.ships;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;


import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.spy;

@Disabled
public class CompVsCompTest {


    @Test
    void totallyRandomVsTotallyRandomRatio() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.TOTALLY_RANDOM);
            game.setAllyAlgorithm(Algorithm.TOTALLY_RANDOM);

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

    @Test
    void randomWithFinishingVsRandomWithFinishingRatio() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.RANDOM_WITH_FINISHING);
            game.setAllyAlgorithm(Algorithm.RANDOM_WITH_FINISHING);

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

    @Test
    void randomWithFinishingVsTotallyRandomRatio() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.TOTALLY_RANDOM);
            game.setAllyAlgorithm(Algorithm.RANDOM_WITH_FINISHING);

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

    @Test
    void finishingEmptySurroundLvl1VsTotallyRandom() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.TOTALLY_RANDOM);
            game.setAllyAlgorithm(Algorithm.FINISHING_EMPTY_SURROUND_LVL1);

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

    @Test
    void finishingEmptySurroundLvl1VsRandomWithFinishing() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.RANDOM_WITH_FINISHING);
            game.setAllyAlgorithm(Algorithm.FINISHING_EMPTY_SURROUND_LVL1);

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

    @Test
    void finishingEmptySurroundLvl1VsFinishingEmptySurroundLvl1() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.FINISHING_EMPTY_SURROUND_LVL1);
            game.setAllyAlgorithm(Algorithm.FINISHING_EMPTY_SURROUND_LVL1);

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

    @Test
    void checkAllPossiblePositionsVsFinishingEmptySurroundLvl1() {

        int countAllyWins = 0;
        int countEnemyWins = 0;

        for (int i = 0; i < 1000; i++) {

            Game game = spy(Game.class);

            willDoNothing().given(game).chooseManualOrAutoSetFleet();
            willDoNothing().given(game).humanOrComputer();
            willDoNothing().given(game).chooseDifficulty();
            willDoNothing().given(game).chooseAllyDifficulty();

            game.setCompVsComp(true);
            game.setAutoSetAllyFleet(true);
            game.setEnemyAlgorithm(Algorithm.CHECK_ALL_POSSIBLE_POSITIONS);
            game.setAllyAlgorithm(Algorithm.FINISHING_EMPTY_SURROUND_LVL1);

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
