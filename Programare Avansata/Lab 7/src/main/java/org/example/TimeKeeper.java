package org.example;


/**
 * Clasa TimeKeeper este responsabila de monitorizarea duratei de desfasurare a jocului si a
 * timpului de raspuns al jucatorilor. Cand jocul incepe, un obiect TimeKeeper este creat si un fir
 * de executie este pornit pentru a urmari timpul scurs. In timp ce jocul continua sa ruleze, timpul
 * este masurat si comparat cu o limita predefinita de 10 secunde. Daca jocul se incheie sau timpul
 * depaseste limita, se afiseaza un mesaj corespunzator, iar firul de executie al TimeKeeper se incheie.
 * Aceasta clasa asigura ca jocul nu ramane intr-o stare de blocare infinita si se termina in cazul in
 * care timpul depaseste limita sau jocul se incheie in mod normal.
 */
public class TimeKeeper implements Runnable {

    private final Game game;

    public TimeKeeper(Game game) {
        this.game = game;
    }


    @Override
    public void run() {
        long start = System.nanoTime();
        while (true) {
            long end = System.nanoTime();

            long elapsedTime = (end - start) / 1_000_000_000;
            if (game.getGameFinished()) {
                System.out.println("Game duration: " + elapsedTime + " seconds");
                return;
            }

            if (elapsedTime > 10) {
                System.out.println("\nTime Limit Exceeded - 10 seconds");
                break;
            }

        }
        System.exit(0);
    }

}