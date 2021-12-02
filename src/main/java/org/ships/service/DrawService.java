package org.ships.service;

import org.ships.Ship;
import org.ships.Vector;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;

public class DrawService {

    public static List<String> drawShipPlacement(Ship ship) {

        String coordinates = drawCoordinates(ship);
        Vector vector = drawVector();

        return FleetService.encodeShipWithCoordinatesAndVector(ship, vector, coordinates);
    }

    private static String drawCoordinates(Ship ship) {

        int shipLength = FleetService.getShipLength(ship);

        int x = drawX(shipLength);
        int y = drawY(shipLength);

        return CoordinatesService.getCoordinatesFromXY(x, y);
    }

    private static int drawX(int shipLength) {

        Random random = new Random();

        OptionalInt optional = random.ints(0, 11 - shipLength).findFirst();

        if (optional.isPresent())
            return optional.getAsInt();

        return 0;
    }


    private static int drawY(int shipLength) {

        Random random = new Random();

        OptionalInt optional = random.ints(0, 11 - shipLength).findFirst();

        if (optional.isPresent())
            return optional.getAsInt();

        return 0;
    }

    private static Vector drawVector() {

        Random random = new Random();

        OptionalInt optionalInt = random.ints(0, 2).findFirst();

        if (optionalInt.isPresent()) {

            if (optionalInt.getAsInt() == 0)
                return Vector.SOUTH;

            else
                return Vector.EAST;
        }

        return null;
    }


}
