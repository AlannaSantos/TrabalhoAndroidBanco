package com.example.books.data;

import java.io.Serializable;

public class Book implements Serializable {

    private int id;
    private String title;
    private String author;
    private String pages;

    public int getId() {
        return id;
    }

    public Book(String title, String author, String pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    public Book(int id, String title, String author, String pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.pages = pages;
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

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPages() {
        return pages;
    }

    public void setPages(String pages) {
        this.pages = pages;
    }
}
