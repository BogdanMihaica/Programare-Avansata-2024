package org.example;

import java.util.ArrayList;
import java.util.List;

public class Sequence {
    public  List<Token> sequence=new ArrayList<>();

    public synchronized void addToken(Token token){
        sequence.add(token);
    }

    public int sizeofSequence(){
        return sequence.size();
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Token token : sequence) {
            sb.append("(").append(token.getFirstNumber()).append("-").append(token.getSecondNumber()).append(") ");
        }
        return sb.toString();
    }
}
