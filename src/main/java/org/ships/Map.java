package org.ships;

public class Map {

    public String constructMaps(Matrix matrixAlly, Matrix matrixEnemy) {

        StringBuilder sb = new StringBuilder();

        sb.append("   A|B|C|D|E|F|G|H|I|J|");
        sb.append("     A|B|C|D|E|F|G|H|I|J|\r\n");

        for (int i = 0; i < 10; i++) {
            sb.append(i + 1);
            if (i < 9)
                sb.append(" ");

            for (int j = 0; j < 10; j++) {
                sb.append("|");

                if (matrixAlly.getMatrix()[i][j].equals("!"))
                    sb.append(" ");
                else
                    sb.append(matrixAlly.getMatrix()[i][j]);
            }

            sb.append("|  ");
            sb.append(i + 1);

            if (i < 9)
                sb.append(" ");

            for (int k = 0; k < 10; k++) {
                sb.append("|");

                if (!matrixEnemy.getMatrix()[i][k].equals("X") && !matrixEnemy.getMatrix()[i][k].equals("."))
                    sb.append(" ");
                else
                    sb.append(matrixEnemy.getMatrix()[i][k]);
            }

            sb.append("|\r\n");
        }

        return sb.toString();
    }
}
