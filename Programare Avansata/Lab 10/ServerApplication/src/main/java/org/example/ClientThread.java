package org.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private GameServer server;
    private int P = -1;
    private boolean myTurn = true;

    public ClientThread(Socket socket, GameServer server) {
        this.clientSocket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
        ) {
            String request;
            while ((request = in.readLine()) != null) {
                System.out.println("Received request: " + request);
                String[] parts = request.split(" ");
                String command = parts[0];

                if ("stop".equalsIgnoreCase(command)) {
                    out.println("Server stopped");
                    System.out.println("Server stopping...");
                    break;
                } else if ("create".equalsIgnoreCase(parts[0])) {
                    if (parts.length > 1) {
                        try {
                            int x = Integer.parseInt(parts[1]);
                            boolean alreadyExists = false;
                            List<Integer> waitingGames = server.getWaitingGames();
                            synchronized (waitingGames) {
                                alreadyExists = waitingGames.contains(x);
                            }
                            if (alreadyExists) {
                                out.println("Game with id " + x + " already exists.");
                                out.flush();
                            } else {
                                Game game = new Game(x);
                                server.addGameToWaitingList(x);
                                P = 1;
                                out.println("Game with id " + x + " was created. Wait for another player to join");
                                out.flush();
                                boolean joined = false;
                                while (!joined) {
                                    List<Integer> requests = server.getJoinRequests();
                                    synchronized (requests) {
                                        for (Integer A : requests) {
                                            if (A == x) {
                                                joined = true;
                                                P = 1;
                                                break;
                                            }
                                        }
                                    }
                                    try {
                                        Thread.sleep(1000); // Sleep to prevent busy-waiting
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                                System.out.println("Player joined the game with ID: " + x); // Debug statement
                                out.println("A player joined your game! Here is your board");
                                out.flush(); // Ensure the message is sent to the client

                                try {
                                    play(x, out, in, 1);
                                } catch (IOException | InterruptedException e) {
                                    throw new RuntimeException(e);
                                }
                                server.removeJoinRequest(x);
                            }
                        } catch (NumberFormatException e) {
                            out.println("Invalid number format: " + parts[1]);
                            out.flush();
                        }
                    } else {
                        out.println("Usage: create x (where x is an integer)");
                        out.flush();
                    }
                } else if ("join".equalsIgnoreCase(parts[0])) {
                    if (parts.length > 1) {
                        try {
                            int x = Integer.parseInt(parts[1]);
                            boolean exists;
                            List<Integer> createdGames = server.getWaitingGames();
                            synchronized (createdGames) {
                                exists = createdGames.contains(x);
                            }
                            if (exists) {
                                server.addJoinRequest(x);
                                Game game = new Game(x);
                                server.addOngoingGame(game);
                                server.removeWaiting(x);
                                myTurn = false;
                                P = 2;
                                play(x, out, in, 2);
                            } else {
                                out.println("Game with id " + x + " does not exist.");
                                out.flush();
                            }
                        } catch (NumberFormatException e) {
                            out.println("Invalid number format: " + parts[1]);
                            out.flush();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    } else {
                        out.println("Usage: join x (where x is an integer)");
                        out.flush();
                    }
                } else {
                    out.println("Server received the request: " + request);
                    out.flush();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void play(int id, PrintWriter out, BufferedReader in, int player) throws IOException, InterruptedException {
        out.println("Game started for game ID: " + id);
        out.flush();
        Game game = server.getOngoingGame(id);
        boolean gameWon = false;

        // Send initial board state to player
        if (player == 1) {
            out.println(game.getA1());
            out.flush();
            out.println("-------------------");
            out.flush();
            out.println(game.getP1());
            out.flush();
        } else if (player == 2) {
            out.println("Successfully joined the game! Here is your board.");
            out.flush();
            out.println(game.getA2());
            out.flush();
            out.println("-------------------");
            out.flush();
            out.println(game.getP2());
            out.flush();
        }

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        ScheduledFuture<?> timer = null;

        while (!gameWon) {
            // Wait for the player's turn
            while (server.getOngoingGame(id).turn != P) {
                Thread.sleep(1000);
            }

            out.println("It's your turn! Enter your attack command (attack x y):");
            out.flush();

            boolean validAttack = false;
            while (!validAttack) {
                if (timer != null) {
                    timer.cancel(true);
                }
                boolean finalValidAttack = validAttack;
                timer = scheduler.schedule(() -> {
                    if (!finalValidAttack) {
                        out.println("Time's up! Turn switched to the other player.");
                        out.flush();
                        server.setTurnForId(id);
                    }
                }, 10, TimeUnit.SECONDS);

                String request = in.readLine();
                String[] parts = request.split(" ");
                String command = parts[0];

                if ("attack".equalsIgnoreCase(command) && parts.length == 3) {
                    try {
                        int x = Integer.parseInt(parts[1]);
                        int y = Integer.parseInt(parts[2]);
                        int targetPlayer = player == 1 ? 2 : 1;

                        validAttack = game.attack(x, y, targetPlayer, out);

                        if (validAttack) {
                            int player1Sunk = game.allSunkenP1();
                            int player2Sunk = game.allSunkenP2();
                            if (player1Sunk != 0) {
                                out.println("Player 2 won the game");
                                out.flush();
                                server.removeOngoing(id);
                                gameWon = true;
                            } else if (player2Sunk != 0) {
                                out.println("Player 1 won the game");
                                out.flush();
                                server.removeOngoing(id);
                                gameWon = true;
                            } else {
                                // Send updated board state to the player
                                if (player == 1) {
                                    out.println(game.getA1());
                                    out.flush();
                                    out.println("-------------------");
                                    out.flush();
                                    out.println(game.getP1());
                                    out.flush();
                                } else if (player == 2) {
                                    out.println(game.getA2());
                                    out.flush();
                                    out.println("-------------------");
                                    out.flush();
                                    out.println(game.getP2());
                                    out.flush();
                                }
                            }
                            server.setTurnForId(id); // Update turn after a valid attack
                            if (timer != null) {
                                timer.cancel(true);
                            }
                        } else {
                            out.println("Invalid attack coordinates. Try again.");
                            out.flush();
                        }
                    } catch (NumberFormatException e) {
                        out.println("Invalid attack format. Usage: attack x y");
                        out.flush();
                    }
                } else {
                    out.println("Unknown command or wrong format. Usage: attack x y");
                    out.flush();
                }
            }
        }

        if (timer != null) {
            timer.cancel(true);
        }
        scheduler.shutdown();
    }
}
