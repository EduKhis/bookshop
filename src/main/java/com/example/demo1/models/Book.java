package com.example.demo1.models;

import com.example.demo1.Genre;

public class Book {
    private int id;
    private String name;
    private String author;
    private String price;
    private String quantity;
    private Genre genre;

    public Book(String name, String author, String price, String quantity, Genre genre) {
        this.name = name;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
    }
    public Book(int id ,String name, String author, String price, String quantity) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
    }
    public Book(int id ,String name, String author, String price, String quantity, Genre genre) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.price = price;
        this.quantity = quantity;
        this.genre = genre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price='" + price + '\'' +
                ", quantity='" + quantity + '\'' +
                ", genre=" + genre +
                '}';
    }
}
