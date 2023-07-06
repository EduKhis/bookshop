package com.example.demo1.models;

import java.util.Date;

public class User {
    private int id;
    private String login;
    private String password;
    private Date date;
    private String email;
    private String role;
    private String book;


    public User(String login, String password, Date date, String email, String role) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;
        this.role=role;
    }
    public User(String login, String password, Date date, String email) {
        this.login = login;
        this.password = password;
        this.date = date;
        this.email = email;

    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
