package com.example.kongsun.mylib.adapter;

/**
 * Created by kongsun on 5/31/17.
 */

public class Book {
    public int id;
    public String title;
    public String author;
    public String thumnailUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumnailUrl() {
        return thumnailUrl;
    }

    public Book(int id, String title, String author, String thumnailUrl){
        this.id = id;
        this.title = title;
        this.author = author;
        this.thumnailUrl = thumnailUrl;

    }
}
