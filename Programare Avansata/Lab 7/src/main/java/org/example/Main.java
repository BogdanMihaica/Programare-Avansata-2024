package org.example;

public class Main {
    public static void main(String[] args) {
        Game tokenGame=new Game();

        tokenGame.addPlayer(new Player("Anton"));
        tokenGame.addPlayer(new Player("Dominic"));
        tokenGame.addPlayer(new Player("Samuel"));
        tokenGame.addPlayer(new Player("Sabin"));
        tokenGame.play();
    }
}