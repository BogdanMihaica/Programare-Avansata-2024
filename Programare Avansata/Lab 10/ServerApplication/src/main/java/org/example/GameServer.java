package org.example;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameServer {
    private int port;
    public List<Integer> waitingGames;
    public List<Integer> joinRequests;
    public List<Game> ongoingGames;
    public Game getOngoingGame(int id)
    {
        for(Game game : ongoingGames)
        {
            if(id==game.getId())
                return game;
        }
        return null;
    }
    public void removeOngoing(Integer id)
    {
        ongoingGames.remove(id);
    }
    public void removeWaiting(Integer id)
    {
        waitingGames.remove(id);
    }
    public void removeJoinRequest(Integer id)
    {
        joinRequests.remove(id);
    }
    public void addOngoingGame(Game id)
    {
        ongoingGames.add(id);
    }
    public void addJoinRequest(int id)
    {
        joinRequests.add(id);
    }
    public List<Integer> getJoinRequests() {
        return joinRequests;
    }

    public List<Game> getOngoingGames() {
        return ongoingGames;
    }

    public void addGameToWaitingList(int A)
    {
        waitingGames.add(A);
    }
    public GameServer(int port) {
        this.port = port;
        waitingGames = Collections.synchronizedList(new ArrayList<>());
        joinRequests = Collections.synchronizedList(new ArrayList<>());
        ongoingGames = Collections.synchronizedList(new ArrayList<>());
    }

    public List<Integer> getWaitingGames() {
        return waitingGames;
    }

    public void start() {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port " + port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket.getInetAddress());
                new ClientThread(clientSocket, this).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setTurnForId(int id)
    {
        for(Game game : ongoingGames)
        {
            if(id==game.getId())
                game.turn=3-game.turn;
        }
    }
    public static void main(String[] args) {
        int port = 12345; // specify your port number here
        GameServer server = new GameServer(port);
        server.start();
    }
}
