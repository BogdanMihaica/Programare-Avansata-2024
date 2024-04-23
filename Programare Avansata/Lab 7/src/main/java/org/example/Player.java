package org.example;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Aceasta clasa Player reprezinta un jucator in cadrul unui joc. Fiecare jucator are un nume,
 * un scor si o secventa de token-uri extrase. Aceasta implementeaza interfata Runnable,
 * permitandu-i sa fie executata intr-un fir de executie separat. Metoda run() contine logica
 * principala a jocului pentru jucator, in care acesta extrage token-uri din sacul de joc si incearca
 * sa formeze secvente valide. Jucatorul poate crea si submite secvente, iar in cazul in care o secventa
 * formata este completa sau jocul se incheie, se afiseaza rezultatele si se determina castigatorul.
 * Clasa utilizeaza sincronizare pentru a evita conditiile de cursa si pentru a asigura ca operatiile
 * sunt realizate in mod corespunzator in cadrul firului de executie al jocului.
 */
public class Player implements Runnable {
    private String name;
    private Game game;
    public List<Token> extracted = new ArrayList<>(); // Each player has their own extracted list
    private int score;
    private Sequence sequence;
    private static final Object lock = new Object();

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.sequence = new Sequence();
    }

    private boolean submitSequence() {

        List<Token> isSequence = createSequence(extracted);
        if (isSequence != null) {
            score = isSequence.size();
            sequence.sequence = isSequence;
        }
        if (isSequence != null && isSequence.size() == game.getPointLimit()) {
            game.setGameFinished(true);
            synchronized (sequence) {
                System.out.print(name + "'s sequence: ");
                for (Token t : sequence.sequence) {
                    System.out.print("(" + t.getFirstNumber() + "-" + t.getSecondNumber() + ") ");
                }
                System.out.println();
            }
            return true;
        }

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isClosedSequence(List<Token> sequence) {
        if (sequence != null && sequence.size() < 2) {
            return false; // A closed sequence must have at least 2 tokens
        } else {
            int start = sequence.get(0).getFirstNumber();
            int end = sequence.get(sequence.size() - 1).getSecondNumber();
            return start == end;
        }
    }

    @Override
    public void run() {
        while (!game.getGameFinished() && !game.getBag().isEmpty()) {
            waitTurn();
            if (!game.getGameFinished() && !game.getBag().isEmpty()) {
                synchronized (game) {
                    if (game.getCurrentTurn() == game.players.indexOf(this)) {
                        Token token = game.getBag().extractToken();
                        System.out.println(name + " pulled token: " + token.getFirstNumber() + "-" + token.getSecondNumber());
                        if (token != null) {
                            synchronized (sequence) {
                                extracted.add(token);
                            }
                            if (submitSequence()) {
                                break;
                            }
                        }
                        game.setCurrentTurn((game.getCurrentTurn() + 1) % game.getPlayers().size());
                    }
                    game.notifyAll();
                }
            }
        }
        // If the game is finished or the bag is empty, print the result
        if (game.getGameFinished() || game.getBag().isEmpty()&&!game.printed) {
            //printEndOfGame();
            game.setGameFinished(true);
            game.printed=true;
            game.showWinner();
        }
    }

    public void waitTurn() {
        synchronized (game) {
            while (game.getCurrentTurn() != game.players.indexOf(this)) {
                try {
                    game.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Token> createSequence(List<Token> extracted) {
        List<Token> longestSequence = new ArrayList<>();
        Set<Token> visited = new HashSet<>();

        for (Token token : extracted) {
            List<Token> currentSequence = new ArrayList<>();
            exploreSequence(extracted, token, currentSequence, longestSequence, visited);
        }

        return longestSequence.isEmpty() ? null : longestSequence;
    }

    private void exploreSequence(List<Token> tokens, Token currentToken, List<Token> currentSequence,
                                 List<Token> longestSequence, Set<Token> visited) {
        visited.add(currentToken);
        currentSequence.add(currentToken);

        Token nextToken = getNextToken(tokens, currentToken);
        if (nextToken != null && !visited.contains(nextToken)) {
            exploreSequence(tokens, nextToken, currentSequence, longestSequence, visited);
        } else if (nextToken != null && isClosedSequence(currentSequence)) {
            if (currentSequence.size() > longestSequence.size()) {
                longestSequence.clear();
                longestSequence.addAll(currentSequence);
            }
        }

        currentSequence.remove(currentSequence.size() - 1);
        visited.remove(currentToken);
    }

    private static Token getNextToken(List<Token> tokens, Token currentToken) {
        int currentEndNumber = currentToken.getSecondNumber();
        for (Token token : tokens) {
            if (token.getFirstNumber() == currentEndNumber) {
                return token;
            }
        }
        return null;
    }



    public String getName() {
        return name;
    }



    public void setGame(Game game) {
        this.game = game;
    }




    public int getScore() {
        return score;
    }


    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", extracted=" + extracted +
                ", score=" + score +
                ", sequence=" + sequence +
                '}';
    }
}
