package org.ships.service;

import org.ships.Ship;
import org.ships.Vector;

import java.util.*;

public class DrawService {

    public static List<String> drawPositionOfShip(Ship ship) {

        List<List<String>> allPossiblePositions = createListOfAllPossiblePositions(ship);
        Random random = new Random();
        int drawnIndex = random.nextInt(allPossiblePositions.size());

        return allPossiblePositions.get(drawnIndex);
    }

    public static List<List<String>> createListOfAllPossiblePositions(Ship ship) {

        List<List<String>> result = new ArrayList<>();

        int shipLength = FleetService.getShipLength(ship);

        for (int i = 0; i < 11 - shipLength; i++)
            for (int j = 0; j < 10; j++)
                result.add(FleetService.encodeShipWithCoordinatesAndVector(
                        ship,
                        Vector.EAST,
                        CoordinatesService.getCoordinatesFromXY(i, j)
                ));

        for (int i = 0; i < 11 - shipLength; i++)
            for (int j = 0; j < 10; j++)
                result.add(FleetService.encodeShipWithCoordinatesAndVector(
                        ship,
                        Vector.SOUTH,
                        CoordinatesService.getCoordinatesFromXY(j, i)
                ));

        return result;

    }

}
