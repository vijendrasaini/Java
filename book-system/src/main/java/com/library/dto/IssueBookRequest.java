package com.library.dto;

public class IssueBookRequest {

    private String bookId;
    private String userId;

    public IssueBookRequest(String bookId, String userId) {
        this.bookId = bookId;
        this.userId = userId;
    }

    public String getBookId() {
        return bookId;
    }

    public String getUserId() {
        return userId;
    }
}