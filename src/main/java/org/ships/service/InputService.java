package org.ships.service;

import org.ships.ship.Ship;
import org.ships.ship.ShipType;

import java.util.List;
import java.util.Scanner;

public class InputService {

    public static List<String> createEncodedShipFromInput(ShipType shipType) {

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (!ValidationService.isShipSetValid(input))
            return null;

        Ship ship = new Ship(shipType, CoordinatesService.getVectorFromShipSet(input));

        return Ship.encodeShipToCoordinates(ship, CoordinatesService.getCoordinatesFromShipSet(input));

    }
}
