package com.example.elibraryadmin.model;

public class Book {
    private int id;
    private String title;
    private String author;
    private int stock;

    public Book(int id, String title, String author, int stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    // Getters and setters
}
