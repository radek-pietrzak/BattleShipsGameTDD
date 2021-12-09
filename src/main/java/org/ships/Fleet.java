package org.ships;

import java.util.LinkedList;
import java.util.List;

public class Fleet {

    private final List<Ship> fleetToSet = new LinkedList<>();
    private List<List<String>> encodedFleet = new LinkedList<>();

    public List<Ship> getFleetToSet() {
        return fleetToSet;
    }

    public List<List<String>> getEncodedFleet() {
        return encodedFleet;
    }

    public void addShipsToFleetToSet() {
        fleetToSet.add(Ship.CARRIER);
        fleetToSet.add(Ship.BATTLESHIP);
        fleetToSet.add(Ship.DESTROYER);
        fleetToSet.add(Ship.SUBMARINE);
        fleetToSet.add(Ship.PATROL_BOAT);
        fleetToSet.add(Ship.PATROL_BOAT);
        fleetToSet.add(Ship.PATROL_BOAT);
    }

    public void addShipsToEncodedFleet(List<String> encodedShip) {
        encodedFleet.add(encodedShip);
    }

    public void removeShipFromListAfterHit(String shot) {

        encodedFleet.forEach(ship -> ship.removeIf(s -> s.contains(shot)));
        encodedFleet.removeIf(List::isEmpty);
    }

    public void removeFirstShipFromFleetToSet() {
        fleetToSet.remove(0);
    }
}
