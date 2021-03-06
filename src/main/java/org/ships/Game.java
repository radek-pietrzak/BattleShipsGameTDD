package org.ships;

import org.ships.service.*;

import java.util.*;

public class Game {

    private final Matrix matrixAlly = new Matrix();
    private final Matrix matrixEnemy = new Matrix();
    private final Matrix computerSeenMatrix = new Matrix();
    private final Matrix computerAsHumanSeenMatrix = new Matrix();
    private final Map map = new Map();
    private final Fleet allyFleet = new Fleet();
    private final Fleet enemyFleet = new Fleet();
    private final Computer computer = new Computer();
    private boolean compVsComp = true;
    private boolean autoSetAllyFleet = false;
    private Algorithm enemyAlgorithm;
    private Algorithm allyAlgorithm;


    public Fleet getAllyFleet() {
        return allyFleet;
    }

    public Fleet getEnemyFleet() {
        return enemyFleet;
    }

    public void setCompVsComp(boolean compVsComp) {
        this.compVsComp = compVsComp;
    }

    public void setAutoSetAllyFleet(boolean autoSetAllyFleet) {
        this.autoSetAllyFleet = autoSetAllyFleet;
    }

    public void setEnemyAlgorithm(Algorithm enemyAlgorithm) {
        this.enemyAlgorithm = enemyAlgorithm;
    }

    public void setAllyAlgorithm(Algorithm allyAlgorithm) {
        this.allyAlgorithm = allyAlgorithm;
    }

    public void startGame() {

        allyFleet.addShipsToFleetToSet();
        enemyFleet.addShipsToFleetToSet();

        chooseManualOrAutoSetFleet();

        chooseDifficulty();

        if (autoSetAllyFleet)
            allySetFleetAuto();
        else
            allySetFleetManual();

        enemySetFleet();

        humanOrComputer();

        if (compVsComp) {
            chooseAllyDifficulty();
        }

        System.out.println(map.constructMaps(matrixAlly, matrixEnemy));

        while (true) {

            if (compVsComp)
                startCompAllyTurn();
            else
                startAllyTurn();

            if (enemyFleet.getEncodedFleet().isEmpty()) {
                System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
                System.out.println("You are the winner!! Congratulations!!");
                break;
            }

            startEnemyTurn();

            if (allyFleet.getEncodedFleet().isEmpty()) {
                System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
                System.out.println("Bad luck. You have lost.");
                break;
            }

            System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
            System.out.println("Size of enemy fleet: " + enemyFleet.getEncodedFleet().size());
            System.out.println("Size of ally fleet: " + allyFleet.getEncodedFleet().size());
        }
    }

    void chooseAllyDifficulty() {
        boolean validAnswer = false;

        Scanner scanner = new Scanner(System.in);
        String input;
        while (!validAnswer) {

            System.out.println("Choose ally difficulty: very easy - \"ve\", easy - \"e\", medium - \"m\", difficult - \"d\", very difficult - \"vd\"");
            input = scanner.nextLine();

            switch (input) {
                case "ve" -> {
                    allyAlgorithm = Algorithm.TOTALLY_RANDOM;
                    validAnswer = true;
                }
                case "e" -> {
                    allyAlgorithm = Algorithm.RANDOM_WITH_FINISHING;
                    validAnswer = true;
                }
                case "m" -> {
                    allyAlgorithm = Algorithm.FINISHING_EMPTY_SURROUND_LVL1;
                    validAnswer = true;
                }
                case "d" -> {
                    allyAlgorithm = Algorithm.CHECK_ALL_POSSIBLE_POSITIONS;
                    validAnswer = true;
                }

                case "vd" -> {
                    allyAlgorithm = Algorithm.ALL_POSS_POS_WITH_OFFSET;
                    validAnswer = true;
                }
            }
        }
    }

    void chooseManualOrAutoSetFleet() {
        boolean autoSetGoodAnswer = false;

        while (!autoSetGoodAnswer) {

            System.out.println("Manual set fleet or auto? \"manual\" \"auto\"");
            Scanner scanner = new Scanner(System.in);
            String setAnswer = scanner.nextLine();

            if (setAnswer.equals("auto")) {
                autoSetGoodAnswer = true;
                autoSetAllyFleet = true;
            } else if (setAnswer.equals("manual"))
                autoSetGoodAnswer = true;
        }


    }

    void chooseDifficulty() {

        boolean validAnswer = false;

        Scanner scanner = new Scanner(System.in);

        while (!validAnswer) {

            System.out.println("Choose enemy difficulty: very easy - \"ve\", easy - \"e\", medium - \"m\", difficult - \"d\", very difficult - \"vd\"");
            String input = scanner.nextLine();

            switch (input) {
                case "ve" -> {
                    enemyAlgorithm = Algorithm.TOTALLY_RANDOM;
                    validAnswer = true;
                }
                case "e" -> {
                    enemyAlgorithm = Algorithm.RANDOM_WITH_FINISHING;
                    validAnswer = true;
                }
                case "m" -> {
                    enemyAlgorithm = Algorithm.FINISHING_EMPTY_SURROUND_LVL1;
                    validAnswer = true;
                }
                case "d" -> {
                    enemyAlgorithm = Algorithm.CHECK_ALL_POSSIBLE_POSITIONS;
                    validAnswer = true;
                }
                case "vd" -> {
                    enemyAlgorithm = Algorithm.ALL_POSS_POS_WITH_OFFSET;
                    validAnswer = true;
                }
            }
        }
    }

    private void allySetFleetManual() {

        while (!allyFleet.getFleetToSet().isEmpty()) {

            System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
            System.out.println("Set " + allyFleet.getFleetToSet().get(0).toString().toLowerCase(Locale.ROOT) + ".");
            List<String> encodedShip = InputService.createEncodedShipFromInput(allyFleet.getFleetToSet().get(0));

            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);


            if (!setShipResult.contains("ERROR")) {
                System.out.println(setShipResult);
                allyFleet.removeFirstShipFromFleetToSet();
                allyFleet.addShipsToEncodedFleet(encodedShip);
            } else
                System.out.println(setShipResult.replace("ERROR", ""));
        }

        System.out.println("Fleet set finished.");
    }

    private void allySetFleetAuto() {

        while (!allyFleet.getFleetToSet().isEmpty()) {

            List<String> encodedShip = DrawService.drawPositionOfShip(allyFleet.getFleetToSet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);

            if (!setShipResult.contains("ERROR")) {
                allyFleet.removeFirstShipFromFleetToSet();
                allyFleet.addShipsToEncodedFleet(encodedShip);
            }
        }
    }

    private void enemySetFleet() {

        while (!enemyFleet.getFleetToSet().isEmpty()) {

            List<String> encodedShip = DrawService.drawPositionOfShip(enemyFleet.getFleetToSet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixEnemy);

            if (!setShipResult.contains("ERROR")) {
                enemyFleet.removeFirstShipFromFleetToSet();
                enemyFleet.addShipsToEncodedFleet(encodedShip);
            }
        }
    }

    void humanOrComputer() {

        boolean validAnswer = false;
        Scanner scanner = new Scanner(System.in);
        String input;

        while (!validAnswer) {
            System.out.println("Human vs Computer \"human\" or Computer vs Computer \"comp\"");
            input = scanner.nextLine();

            if (input.equals("comp")) {
                compVsComp = true;
                validAnswer = true;
            } else if (input.equals("human")) {
                compVsComp = false;
                validAnswer = true;
            }

        }
    }

    private void startCompAllyTurn() {

        Matrix.copyAllShots(matrixEnemy, computerAsHumanSeenMatrix);

        int[] compShotChoice = computer.compShotChoice(computerAsHumanSeenMatrix, allyAlgorithm);
        int x = compShotChoice[0];
        int y = compShotChoice[1];

        String shot = ShootService.shootComp(matrixEnemy, x, y);

        if (shot.contains("Computer missed"))
            System.out.println(shot);

        else {
            enemyFleet.removeShipFromListAfterHit(shot);
            System.out.println("Computer hits " + CoordinatesService.getCoordinatesFromXY(x, y) + ".");
        }
    }

    private void startAllyTurn() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String shot = ShootService.shoot(matrixEnemy, input);
        if (shot.equals("Incorrect coordinates") || shot.contains("Missed"))
            System.out.println(shot);
        else {
            enemyFleet.removeShipFromListAfterHit(shot);
            System.out.println("Hit!!");
        }
    }

    private void startEnemyTurn() {

        Matrix.copyAllShots(matrixAlly, computerSeenMatrix);

        int[] compShotChoice = computer.compShotChoice(computerSeenMatrix, enemyAlgorithm);
        int x = compShotChoice[0];
        int y = compShotChoice[1];

        String shot = ShootService.shootComp(matrixAlly, x, y);

        if (shot.contains("Computer missed"))
            System.out.println(shot);

        else {
            allyFleet.removeShipFromListAfterHit(shot);
            System.out.println("Computer hits " + CoordinatesService.getCoordinatesFromXY(x, y) + ".");
        }
    }
}
