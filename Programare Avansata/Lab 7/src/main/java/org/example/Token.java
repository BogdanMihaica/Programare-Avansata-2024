package org.example;

public class Token {
    private final int firstNumber;
    private final int secondNumber;

    public Token(int nr1, int nr2){
        this.firstNumber=nr1;
        this.secondNumber=nr2;
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }
    @Override
    public String toString() {
        return "(" + firstNumber + "-" + secondNumber + ")";
    }
}
