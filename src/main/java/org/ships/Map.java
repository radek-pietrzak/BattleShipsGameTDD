package org.ships;

public class Map {


    public String drawMap(Matrix matrix) {

        StringBuilder sb = new StringBuilder();

        sb.append("   A|B|C|D|E|F|G|H|I|J|\r\n");

        for (int i = 0; i < 10; i++) {
            sb.append(i + 1);
            if (i < 9)
                sb.append(" ");
            for (int j = 0; j < 10; j++) {
                sb.append("|");
                sb.append(matrix.getMatrix()[j][i]);
            }
            sb.append("|\r\n");
        }

        return sb.toString();
    }
}
