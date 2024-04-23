package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Clasa Bag gestioneaza un sac de token-uri utilizat in cadrul jocului.
 * Atunci cand jucatorii extrag token-uri din sac, aceasta clasa asigura ca extragerile
 * sunt gestionate in mod corespunzator si intr-un mod sigur din punct de vedere al concurentei.
 * Metoda extractToken() este utilizata pentru a extrage un token din sac. Ea este sincronizata
 * pentru a evita conflictele de acces la lista de token-uri. Token-ul extras este ales aleatoriu
 * din lista de token-uri disponibile si apoi eliminat din sac pentru a evita extrageri multiple.
 * Metoda returnToken(Token token) este utilizata pentru a returna un token in sac in cazul in care
 * acesta este pus inapoi, de exemplu, atunci cand o secventa nu este completa sau jucatorul decide
 * sa renunte la un token. Clasa Bag asigura ca operatiile de extragere si returnare a token-urilor
 * sunt realizate in mod sigur in contextul unui joc cu mai multi jucatori care ruleaza simulta
 */
public class Bag {
    private final List<Token> tokens = new ArrayList<>();
    private final Object lock = new Object();


    public synchronized Token extractToken() {
        Token extracted = new Token(0, 0);
        if (tokens.isEmpty()) return null;

        synchronized (lock) {
            Random rd = new Random();
            int randomIndex = rd.nextInt(tokens.size());

            extracted = tokens.get(randomIndex);
            tokens.remove(randomIndex);
        }
        return extracted;
    }
    public Bag(int n) {
        Random random = new Random();
        for (int i = 0; i < n; i++) {
            int firstNumber = random.nextInt(4) + 1;
            int secondNumber;
            do {
                secondNumber = random.nextInt(4) + 1;
            } while (secondNumber == firstNumber);

            Token token = new Token(firstNumber, secondNumber);
            tokens.add(token);
        }
    }




    public List<Token> getTokens() {
        return tokens;
    }

    public boolean isEmpty(){
        return tokens.isEmpty();
    }
    public void returnToken(Token token) {
        synchronized (lock) {
            tokens.add(token);
        }
    }
}
