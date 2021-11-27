package org.ships;

public class Map {

    public String drawMaps(Matrix matrixAlly, Matrix matrixEnemy) {

        StringBuilder sb = new StringBuilder();

        sb.append("   A|B|C|D|E|F|G|H|I|J|");
        sb.append("     A|B|C|D|E|F|G|H|I|J|\r\n");

        for (int i = 0; i < 10; i++) {
            sb.append(i + 1);
            if (i < 9)
                sb.append(" ");

            for (int j = 0; j < 10; j++) {
                sb.append("|");
                sb.append(matrixAlly.getMatrix()[j][i]);
            }

            sb.append("|  ");
            sb.append(i + 1);
            if (i < 9)
                sb.append(" ");

            for (int k = 0; k < 10; k++) {
                sb.append("|");
                sb.append(matrixEnemy.getMatrix()[k][i]);
            }

            sb.append("|\r\n");
        }

        return sb.toString();
    }
}
