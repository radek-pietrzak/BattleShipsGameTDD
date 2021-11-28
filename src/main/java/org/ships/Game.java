package org.ships;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Game {

    private final Map map = new Map();
    private final List<Ship> allyShips = new ArrayList<>();
    private final List<Ship> enemyShips = new ArrayList<>();

    public void startGame(Matrix matrixAlly, Matrix matrixEnemy) {

        map.drawMaps(matrixAlly, matrixEnemy);

        while (!allyShips.isEmpty() || !enemyShips.isEmpty()) {
            matrixEnemy.shootPosition(allyTurn());
        }

    }

    private String allyTurn() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

}
