package org.ships;

import java.util.LinkedList;
import java.util.List;

public class Fleet {

    List<Ship> fleet = new LinkedList<>();

    public List<Ship> getFleet() {
        return fleet;
    }

    public void addShipsToFleet() {
        fleet.add(Ship.CARRIER);
        fleet.add(Ship.BATTLESHIP);
        fleet.add(Ship.DESTROYER);
        fleet.add(Ship.SUBMARINE);
        fleet.add(Ship.PATROL_BOAT);
        fleet.add(Ship.PATROL_BOAT);
        fleet.add(Ship.PATROL_BOAT);
    }

    public void removeFirstShipsFromFleet() {
        fleet.remove(0);
    }
}
