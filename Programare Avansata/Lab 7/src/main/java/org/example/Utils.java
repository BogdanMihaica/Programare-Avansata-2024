package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Utils {

    public static List<Token> createSequence(List<Token> extracted) {
        List<Token> longestSequence = new ArrayList<>();
        Set<Token> visited = new HashSet<>();

        for (Token token : extracted) {
            List<Token> currentSequence = new ArrayList<>();
            exploreSequence(extracted, token, currentSequence, longestSequence, visited);
        }

        return longestSequence.isEmpty() ? null : longestSequence;
    }

    private static void exploreSequence(List<Token> tokens, Token currentToken, List<Token> currentSequence,
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

    private static boolean isClosedSequence(List<Token> sequence) {
        int start = sequence.get(0).getFirstNumber();
        int end = sequence.get(sequence.size() - 1).getSecondNumber();
        return start == end;
    }

    public static void main(String[] args) {
        List<Token> extracted = new ArrayList<>();
        extracted.add(new Token(1, 2));
        extracted.add(new Token(3,4));
        extracted.add(new Token(4, 1));
        extracted.add(new Token(2, 3));
        extracted.add(new Token(1, 4));
        extracted.add(new Token(2, 1));

        List<Token> sequence = createSequence(extracted);
        System.out.println("Greedy sequence: " + sequence);
    }
}
