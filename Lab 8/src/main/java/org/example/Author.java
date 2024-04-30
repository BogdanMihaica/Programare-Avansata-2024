package org.example;

public class Author {
    public int getid() {
        return id;
    }

    public Author(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setid(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    int id;
    String name;
}
