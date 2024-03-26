package org.example;

public class Driver extends Person{
    protected Passenger passenger = new Passenger();
    protected Destination destination = new Destination();

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    @Override
    public Destination getDestination() {
        return destination;
    }

    @Override
    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public Driver(int age, String name) {
        super(age, name);
    }
    public Driver(){}

    @Override
    public String toString() {
        return (this.getName()+", "+this.getAge()+" years");
    }
}