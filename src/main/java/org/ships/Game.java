package org.ships;

import org.ships.service.DrawService;
import org.ships.service.InputService;
import org.ships.service.FleetService;
import org.ships.service.ShootService;

import java.util.*;

public class Game {

    private final Matrix matrixAlly = new Matrix();
    private final Matrix matrixEnemy = new Matrix();
    private final Map map = new Map();
    private final Fleet allyFleet = new Fleet();
    private final Fleet enemyFleet = new Fleet();

    public void startGame() {

        allyFleet.addShipsToFleet();
        enemyFleet.addShipsToFleet();

        chooseManualOrAutoSetFleet();

        enemySetFleet();

        System.out.println(map.constructMaps(matrixAlly, matrixEnemy));

        for (int i = 0; i < 100; i++) {
            startAllyTurn();
            System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
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
                allyFleet.removeFirstShipFromFleet();
            } else
                System.out.println(setShipResult.replace("ERROR", ""));
        }

        System.out.println("Fleet set finished.");
    }

    private void allySetFleetAuto() {

        while (!allyFleet.getFleetToSet().isEmpty()) {

            List<String> encodedShip = DrawService.drawEncodedShip(allyFleet.getFleetToSet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);

            if (!setShipResult.contains("ERROR"))
                allyFleet.removeFirstShipFromFleet();
        }
    }

    private void enemySetFleet() {

        while (!enemyFleet.getFleetToSet().isEmpty()) {

            List<String> encodedShip = DrawService.drawEncodedShip(enemyFleet.getFleetToSet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixEnemy);

            if (!setShipResult.contains("ERROR"))
                enemyFleet.removeFirstShipFromFleet();
        }
    }

    private void startAllyTurn() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(ShootService.shoot(matrixEnemy, input));
    }
}
