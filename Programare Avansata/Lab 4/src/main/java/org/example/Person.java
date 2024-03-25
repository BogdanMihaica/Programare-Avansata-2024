package org.example;

public abstract class Person {
    private int age;
    private Destination destination = new Destination();
    private String name;

    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
        this.destination = destination;
    }

    public int getAge() {
        return age;
    }
    public Person(){}
    public Person(int age, String name) {
        this.age = age;
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
