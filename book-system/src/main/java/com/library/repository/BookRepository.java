package com.library.repository;

import com.library.model.Book;

import java.util.HashMap;
import java.util.Map;

public class BookRepository {

    private final Map<String, Book> bookStore = new HashMap<>();

    public void save(Book book) {
        bookStore.put(book.getId(), book);
    }

    public Book findById(String id) {
        return bookStore.get(id);
    }
}