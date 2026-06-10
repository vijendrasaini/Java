package com.library.model;

public class Book {

    private String id;
    private String title;
    private String author;

    private boolean isIssued;
    private String issuedToUserId; // NEW

    public Book(String id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isIssued = false;
    }

    public String getId() {
        return id;
    }

    public boolean isIssued() {
        return isIssued;
    }

    public String getIssuedToUserId() {
        return issuedToUserId;
    }

    public void issueTo(String userId) {
        this.isIssued = true;
        this.issuedToUserId = userId;
    }

    public void returnBook() {
        this.isIssued = false;
        this.issuedToUserId = null;
    }
}