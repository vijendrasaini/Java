package com.library.dto;

public class IssueBookResponse {

    private String bookId;
    private String userId;
    private String message;

    public IssueBookResponse(String bookId, String userId, String message) {
        this.bookId = bookId;
        this.userId = userId;
        this.message = message;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }

    public String getMessage() {
        return message;
    }
}