package org.ships;

public class Matrix {

    private final String[][] matrix = {
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",},
            {" ", " ", " ", " ", " ", " ", " ", " ", " ", " ",}
    };

    public String[][] getMatrix() {
        return matrix;
    }

    public void addToMatrix(int x, int y, String symbol) {

        if (x >= 0 && x < 10 && y >= 0 && y < 10)
            matrix[y][x] = symbol;
    }

    public static boolean isShipInCoordinates(String symbol) {
        return symbol.equals("C") || symbol.equals("B") || symbol.equals("D") || symbol.equals("S") || symbol.equals("P");
    }

    public static boolean isOffsetInCoordinates(String symbol) {
        return symbol.equals("!");
    }

    public static void copyAllShots(Matrix from, Matrix to) {

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10; j++) {

                if (from.getMatrix()[j][i].equals(".") ||
                        from.getMatrix()[j][i].equals("X"))

                    to.addToMatrix(i, j, from.getMatrix()[j][i]);
            }
    }

}

