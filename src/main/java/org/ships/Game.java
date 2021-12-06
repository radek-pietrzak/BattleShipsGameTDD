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
    private boolean compVsComp = false;

    public void startGame() {

        allyFleet.addShipsToFleetToSet();
        enemyFleet.addShipsToFleetToSet();

        chooseManualOrAutoSetFleet();

        enemySetFleet();

        humanOrComputer();

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

    private void chooseManualOrAutoSetFleet() {
        boolean autoSetGoodAnswer = false;
        boolean autoSetAnswer = false;

        while (!autoSetGoodAnswer) {

            System.out.println("Manual set fleet or auto? \"manual\" \"auto\"");
            Scanner scanner = new Scanner(System.in);
            String setAnswer = scanner.nextLine();

            if (setAnswer.equals("auto")) {
                autoSetGoodAnswer = true;
                autoSetAnswer = true;
            } else if (setAnswer.equals("manual"))
                autoSetGoodAnswer = true;
        }

        if (autoSetAnswer)
            allySetFleetAuto();
        else
            allySetFleetManual();
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

    private void humanOrComputer() {
        System.out.println("Human vs Computer \"human\" or Computer vs Computer \"comp\"");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        compVsComp = input.equals("comp");
    }

    private void startCompAllyTurn() {

        Matrix.copyAllShots(matrixEnemy, computerAsHumanSeenMatrix);

        int[] compShotChoice = computer.compShotChoice(computerAsHumanSeenMatrix);
        int x = compShotChoice[0];
        int y = compShotChoice[1];

        String shot = ShootService.shootComp(matrixEnemy, x, y);

        if (shot.equals("Computer missed."))
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
        if (shot.equals("Incorrect coordinates") || shot.equals("Missed."))
            System.out.println(shot);
        else {
            enemyFleet.removeShipFromListAfterHit(shot);
            System.out.println("Hit!!");
        }
    }

    private void startEnemyTurn() {

        Matrix.copyAllShots(matrixAlly, computerSeenMatrix);

        int[] compShotChoice = computer.compShotChoice(computerSeenMatrix);
        int x = compShotChoice[0];
        int y = compShotChoice[1];

        String shot = ShootService.shootComp(matrixAlly, x, y);

        if (shot.equals("Computer missed."))
            System.out.println(shot);

        else {
            allyFleet.removeShipFromListAfterHit(shot);
            System.out.println("Computer hits " + CoordinatesService.getCoordinatesFromXY(x, y) + ".");
        }
    }
}
