package org.ships;

import org.ships.service.DrawService;
import org.ships.service.InputService;
import org.ships.service.FleetService;

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

        while (!allyFleet.getFleet().isEmpty()) {

            System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
            System.out.println("Set " + allyFleet.getFleet().get(0).toString().toLowerCase(Locale.ROOT) + ".");
            List<String> encodedShip = InputService.createEncodedShipFromInput(allyFleet.getFleet().get(0));

            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);

            if (!setShipResult.contains("ERROR")) {
                System.out.println(setShipResult);
                allyFleet.removeFirstShipsFromFleet();
            } else
                System.out.println(setShipResult.replace("ERROR", ""));
        }

        System.out.println("Fleet set finished.");
    }

    private void allySetFleetAuto() {

        while (!allyFleet.getFleet().isEmpty()) {

            List<String> encodedShip = DrawService.drawEncodedShip(allyFleet.getFleet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);

            if (!setShipResult.contains("ERROR"))
                allyFleet.removeFirstShipsFromFleet();
        }
    }

    private void enemySetFleet() {

        while (!enemyFleet.getFleet().isEmpty()) {

            List<String> encodedShip = DrawService.drawEncodedShip(enemyFleet.getFleet().get(0));
            String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixEnemy);

            if (!setShipResult.contains("ERROR"))
                enemyFleet.removeFirstShipsFromFleet();
        }
    }
}
