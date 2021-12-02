package org.ships;

import org.ships.service.CoordinatesService;
import org.ships.service.InputService;
import org.ships.service.ShipService;
import org.ships.service.ShootService;
import org.ships.ship.Ship;
import org.ships.ship.ShipType;

import java.util.*;

public class Game {

    private final Map map = new Map();
    private final Fleet fleet = new Fleet();
    private final ShootService shootService = new ShootService();
    private final List<ShipType> shipTypes = new LinkedList<>();
    private final List<Ship> allyShips = new ArrayList<>();
    private final List<Ship> enemyShips = new ArrayList<>();

    public List<ShipType> getShipTypes() {
        return shipTypes;
    }

    public void startGame(Matrix matrixAlly, Matrix matrixEnemy) {

        map.drawMaps(matrixAlly, matrixEnemy);

        while(!fleet.getFleet().isEmpty()){

            List<String> encodedShip = InputService.createEncodedShipFromInput(fleet.getFleet().get(0));
            ShipService.addEncodedShipToMatrix(encodedShip, matrixAlly);

            fleet.removeFirstShipTypeFromFleet();
        }

    }

    private String allyTurn() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
