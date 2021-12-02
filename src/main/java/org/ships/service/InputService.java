package org.ships.service;

import org.ships.Ship;
import org.ships.Vector;

import java.util.List;
import java.util.Scanner;

public class InputService {

    public static List<String> createEncodedShipFromInput(Ship ship) {

        String input = getInput();

        if (!ValidationService.isShipSetValid(input))
            return null;

        String coordinates = CoordinatesService.getCoordinatesFromShipSet(input);
        Vector vector = CoordinatesService.getVectorFromShipSet(input);

        return FleetService.encodeShipWithCoordinatesAndVector(ship, vector, coordinates);

    }

    private static String getInput() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
