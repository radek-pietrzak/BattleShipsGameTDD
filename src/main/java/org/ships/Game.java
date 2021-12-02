package org.ships;

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

        allyFleet.addShipTypesToFleet();

        while (!allyFleet.getFleet().isEmpty()) {
            allySetFleetTurn();
        }

        System.out.println(map.constructMaps(matrixAlly, matrixEnemy));

        enemyFleet.addShipTypesToFleet();

        while (!enemyFleet.getFleet().isEmpty()) {
            enemySetFleetTurn();
        }

    }

    private void allySetFleetTurn() {

        System.out.println(map.constructMaps(matrixAlly, matrixEnemy));
        System.out.println("Set " + allyFleet.getFleet().get(0).toString().toLowerCase(Locale.ROOT) + ".");
        List<String> encodedShip = InputService.createEncodedShipFromInput(allyFleet.getFleet().get(0));

        String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixAlly);

        if (!setShipResult.contains("ERROR")) {
            System.out.println(setShipResult);
            allyFleet.removeFirstShipTypeFromFleet();
        } else {
            System.out.println(setShipResult.replace("ERROR", ""));
        }
    }

    private void enemySetFleetTurn() {

        List<String> encodedShip = InputService.createEncodedShipFromInput(enemyFleet.getFleet().get(0));


        String setShipResult = FleetService.addEncodedShipToMatrix(encodedShip, matrixEnemy);

        if (!setShipResult.contains("ERROR")) {
            System.out.println(setShipResult);
            allyFleet.removeFirstShipTypeFromFleet();
        } else {
            System.out.println(setShipResult.replace("ERROR", ""));
        }
    }
}
