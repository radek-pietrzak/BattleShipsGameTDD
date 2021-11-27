package org.ships;

public class Ship {

    private ShipType shipType;
    private Vector vector;

    public Ship(ShipType shipType, Vector vector) {
        this.shipType = shipType;
        this.vector = vector;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public Vector getVector() {
        return vector;
    }
}
