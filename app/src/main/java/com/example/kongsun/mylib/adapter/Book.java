package com.example.kongsun.mylib.adapter;

/**
 * Created by kongsun on 5/31/17.
 */

public class Book {
    public String title;
    public String author;
    public String rate;
    public String thumnail;

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

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getThumnail() {
        return thumnail;
    }

    public void setThumnail(String thumnail) {
        this.thumnail = thumnail;
    }

    public Book(String title, String author, String rate, String thumnail){
        this.title = title;
        this.author = author;
        this.rate = rate;
        this.thumnail = thumnail;

    }
}
