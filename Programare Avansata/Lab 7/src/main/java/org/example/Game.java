package org.example;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Aceasta clasa Game gestioneaza intregul flux de joc si interactiunea intre jucatori.
 * Ea detine un sac de joc si o lista de jucatori care participa la acea runda specifica.
 * Metoda addPlayer(Player player) adauga un jucator la joc, asigurandu-se ca jucatorul nu este deja in joc
 * si extrage un token pentru acesta din sac. Metoda play() initiaza jocul, creand un fir de executie pentru
 * fiecare jucator si un cronometru. In timpul jocului, fiecare jucator ruleaza in propriul fir de executie,
 * extragand si manipuland token-uri pentru a forma secvente. Metoda showWinner() afiseaza rezultatele jocului,
 * sortand jucatorii dupa scor si determinand castigatorul. Clasa utilizeaza sincronizare pentru a evita conditiile
 * de cursa si pentru a asigura o executie corecta si coerenta a jocului in mai multe fire de executie.
 */
public class Game {
    public final Bag bag=new Bag(20);

    public final List<Player> players=new ArrayList<>();

    private boolean gameFinished=false;
    public boolean printed=false;
    private int currentTurn=0;
    private static final Object lock = new Object();

    public int pointLimit=4;
    public void addPlayer(Player player){
        if(!players.contains(player)){
            players.add(player);
            player.setGame(this);
            Token ext= this.bag.extractToken();
            System.out.println(player.getName() + " pulled token: " + ext.getFirstNumber() + "-" + ext.getSecondNumber());
            player.extracted.add(ext);

        } else{
            System.out.println("Player "+ player.getName()+" is already in the game.");
        }
    }

    public void play(){
        TimeKeeper timeKeeper = new TimeKeeper(this);
        Thread tkThread= new Thread(timeKeeper);
        tkThread.setDaemon(true);
        tkThread.start();

        for(Player player : players){
            Thread t1= new Thread(player);
            t1.start();
        }
    }

    public void showWinner() {
        setGameFinished(true);
        synchronized (lock) {
            setGameFinished(true);
            if (players.size() > 0) {
                for(Player player: players){
                    System.out.println(player);
                }
                players.sort(Comparator.comparingInt(Player::getScore).reversed());
                if (players.get(0).getScore() == 0) {
                    System.out.println("No one won!");
                } else {
                    System.out.println("The winner is: " + players.get(0).getName() + " having " + players.get(0).getScore() + ".");
                }
                players.clear();
            }
        }
    }



    public Bag getBag() {
        return bag;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public boolean getGameFinished() {
        return gameFinished;
    }

    public void setGameFinished(boolean gameFinished) {
        this.gameFinished = gameFinished;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(int currentTurn) {
        this.currentTurn = currentTurn;
    }

    public int getPointLimit() {
        return pointLimit;
    }


}
