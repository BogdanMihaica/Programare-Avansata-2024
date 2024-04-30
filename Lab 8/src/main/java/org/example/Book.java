package org.example;

public class Book {
    int id;
    String title;
    String language;
    String publication_date;
    int pages;

    public Book(int id, String title, String language, String publication_date, int pages, int author_id) {
        this.id = id;
        this.title = title;
        this.language = language;
        this.publication_date = publication_date;
        this.pages = pages;
        this.author_id=author_id;
    }

    int author_id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(String publication_date) {
        this.publication_date = publication_date;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }
}
