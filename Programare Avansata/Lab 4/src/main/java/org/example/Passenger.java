package org.example;

public class Passenger extends Person implements Comparable<Passenger>{
    public Passenger(int age, String name) {
        super(age, name);
    }
    public Passenger(){}

    @Override
    public int compareTo(Passenger o) {
        return this.getName().compareTo(o.getName());
    }
    @Override
    public String toString() {
        return (this.getName()+", "+this.getAge()+" years");
    }
}
