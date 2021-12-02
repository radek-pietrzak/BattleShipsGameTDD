package org.ships;

import org.ships.service.InputService;
import org.ships.service.ShipService;
import org.ships.ship.ShipType;

import java.util.*;

public class Game {

    private final Matrix matrixAlly = new Matrix();
    private final Matrix matrixEnemy = new Matrix();
    private final Map map = new Map();
    private final Fleet allyFleet = new Fleet();
    private final Fleet enemyFleet = new Fleet();
    private final List<ShipType> shipTypes = new LinkedList<>();

    public List<ShipType> getShipTypes() {
        return shipTypes;
    }

    public void startGame() {

        allyFleet.addShipTypesToFleet();

        while (!allyFleet.getFleet().isEmpty()) {
            allySetFleetTurn();
        }

        System.out.println(map.drawMaps(matrixAlly, matrixEnemy));

        enemyFleet.addShipTypesToFleet();

        while (!enemyFleet.getFleet().isEmpty()) {
            enemySetFleetTurn();
        }



    }

    private void allySetFleetTurn() {
        System.out.println(map.drawMaps(matrixAlly, matrixEnemy));
        System.out.println("Set " + allyFleet.getFleet().get(0).toString().toLowerCase(Locale.ROOT) + ".");
        List<String> encodedShip = InputService.createEncodedShipFromInput(allyFleet.getFleet().get(0));
        ShipService.addEncodedShipToMatrix(encodedShip, matrixAlly);
        allyFleet.removeFirstShipTypeFromFleet();
    }

    private void enemySetFleetTurn() {

    }
}
