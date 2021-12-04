package org.ships;

import java.util.LinkedList;
import java.util.List;

public class Fleet {

    List<Ship> fleetToSet = new LinkedList<>();

    public List<Ship> getFleetToSet() {
        return fleetToSet;
    }

    public void addShipsToFleet() {
        fleetToSet.add(Ship.CARRIER);
        fleetToSet.add(Ship.BATTLESHIP);
        fleetToSet.add(Ship.DESTROYER);
        fleetToSet.add(Ship.SUBMARINE);
        fleetToSet.add(Ship.PATROL_BOAT);
        fleetToSet.add(Ship.PATROL_BOAT);
        fleetToSet.add(Ship.PATROL_BOAT);
    }

    public void removeFirstShipFromFleet() {
        fleetToSet.remove(0);
    }
}
