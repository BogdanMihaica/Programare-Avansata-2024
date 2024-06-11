package org.example;

import java.io.PrintWriter;

public class Game {

    public int turn=1;
    public int id;
    public Board P1;
    public Board P2;

    public Board getP1() {
        return P1;
    }

    public Board getP2() {
        return P2;
    }

    public Board getA1() {
        return A1;
    }

    public Board getA2() {
        return A2;
    }

    public Board A1;
    public Board A2;
    public int getId(){
        return id;
    }
    public Game(int id)
    {

        this.id=id;
        P1=new Board();
        P2=new Board();
        A1=new Board();
        A2=new Board();
        P1.generate();
        P2.generate();
        A1.generateA();
        A2.generateA();

    }
    public boolean attack(int x, int y, int player, PrintWriter out)
    {
        if(x<0 || x>9)return false;
        if(player==1)
        {

            if(P1.getM()[x][y]>='0' && P1.getM()[x][y]<='9')
            {
                out.println("Bingo!");
                out.flush();
                P1.set(x,y,'X');
                A2.set(x,y,'X');

            }
            else{
            out.println("Oops!");
            out.flush();
                A2.set(x,y,'O');
             }
        }
        else{

            if(P2.getM()[x][y]>='0' && P2.getM()[x][y]<='9')
            {
                out.println("Bingo!");
                out.flush();
                P2.set(x,y,'X');
                A1.set(x,y,'X');
            }
            else{
                A1.set(x,y,'O');
                out.println("Oops!");

                out.flush();
            }
        }
        return true;
    }
    public int allSunkenP1()
    {
        int sunken=1;
        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                if(P1.getM()[i][j]>='0' && P1.getM()[i][j]<='9')
                    sunken=0;
            }
        }

        return sunken;
    }
    public int allSunkenP2()
    {
        int sunken=2;

        for(int i=0; i<10; i++)
        {
            for(int j=0; j<10; j++)
            {
                if(P2.getM()[i][j]>='0' && P2.getM()[i][j]<='9')
                    sunken=0;
            }
        }
        return sunken;
    }
}
