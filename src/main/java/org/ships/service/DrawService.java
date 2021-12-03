package org.ships.service;

import org.ships.Ship;
import org.ships.Vector;

import java.util.*;

public class DrawService {

    private static final List<List<String>> allPossiblePositions = new ArrayList<>();

    public static List<List<String>> getAllPossiblePositions() {
        return allPossiblePositions;
    }

    public static List<String> drawEncodedShip(Ship ship) {

        allPossiblePositions.clear();
        createListOfAllPossiblePositions(ship);
        Random random = new Random();
        int drawnIndex = random.nextInt(allPossiblePositions.size());

        return allPossiblePositions.get(drawnIndex);
    }

    private static void createListOfAllPossiblePositions(Ship ship) {

        int shipLength = FleetService.getShipLength(ship);

        for (int i = 0; i < 11 - shipLength; i++)
            for (int j = 0; j < 10; j++)
                allPossiblePositions.add(FleetService.encodeShipWithCoordinatesAndVector(
                        ship,
                        Vector.EAST,
                        CoordinatesService.getCoordinatesFromXY(i, j)
                ));

        for (int i = 0; i < 11 - shipLength; i++)
            for (int j = 0; j < 10; j++)
                allPossiblePositions.add(FleetService.encodeShipWithCoordinatesAndVector(
                        ship,
                        Vector.SOUTH,
                        CoordinatesService.getCoordinatesFromXY(j, i)
                ));

    }

}
