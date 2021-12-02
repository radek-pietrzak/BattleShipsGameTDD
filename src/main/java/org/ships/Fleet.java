package org.ships;

import org.ships.ship.ShipType;

import java.util.LinkedList;
import java.util.List;

public class Fleet {

    List<ShipType> fleet = new LinkedList<>();

    public List<ShipType> getFleet() {
        return fleet;
    }

    public void addShipTypesToFleet() {
        fleet.add(ShipType.CARRIER);
        fleet.add(ShipType.BATTLESHIP);
        fleet.add(ShipType.DESTROYER);
        fleet.add(ShipType.SUBMARINE);
        fleet.add(ShipType.PATROL_BOAT);
        fleet.add(ShipType.PATROL_BOAT);
        fleet.add(ShipType.PATROL_BOAT);
    }

    public void removeFirstShipTypeFromFleet() {
        fleet.remove(0);
    }
}
