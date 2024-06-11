package org.example;

import java.util.Arrays;
import java.util.Random;

public class Board {
    private char[][] m;
    private char c = '0';

    public Board() {
        m = new char[10][10];
        generateA(); // Initialize the board with '-'
    }

    public char[][] getM() {
        return m;
    }

    public void generateShips(int size, int number) {
        Random random = new Random();
        for (int i = 1; i <= number; i++) {
            int orientation = random.nextInt(2);
            int randomStartX, randomStartY;
            boolean found = false;
            if (orientation == 0) { // Horizontal orientation
                while (!found) {
                    found = true;
                    randomStartX = random.nextInt(10);
                    randomStartY = random.nextInt(10 - size + 1);
                    for (int j = 0; j < size; j++) {
                        if (m[randomStartX][randomStartY + j] != '-') {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        for (int j = 0; j < size; j++) {
                            m[randomStartX][randomStartY + j] = c;
                        }
                    }
                }
            } else { // Vertical orientation
                while (!found) {
                    found = true;
                    randomStartX = random.nextInt(10 - size + 1);
                    randomStartY = random.nextInt(10);
                    for (int j = 0; j < size; j++) {
                        if (m[randomStartX + j][randomStartY] != '-') {
                            found = false;
                            break;
                        }
                    }
                    if (found) {
                        for (int j = 0; j < size; j++) {
                            m[randomStartX + j][randomStartY] = c;
                        }
                    }
                }
            }
            c++;
        }
    }

    public void generate() {
        this.generateA();
        int five = 1;
        int four = 2;
        int three = 3;
        int two = 4;
        generateShips(2, two);
        generateShips(3, three);
        generateShips(4, four);
        generateShips(5, five);
    }

    public void generateA() {
        for (int i = 0; i < 10; i++) {
            Arrays.fill(m[i], '-');
        }
    }

    public void display() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(m[i][j] + " ");
            }
            System.out.println();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                sb.append(m[i][j]).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    public void set(int x, int y, char c)
    {
        m[x][y]=c;
    }
}