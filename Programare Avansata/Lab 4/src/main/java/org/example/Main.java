package org.example;

public class Main {
    public static void main(String[] args) {
        Problem A = new Problem();
        A.generateRandomGroup(50);
        A.generateDestinations(10);
        A.generateDestinationMap();
        A.printDestinationMap();
        A.assignPassengersToDrivers();
        A.printDriverPassengers();
        A.printDestinationMap();

    }
}