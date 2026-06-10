package com.library;

import com.library.dto.IssueBookRequest;
import com.library.dto.IssueBookResponse;
import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;
import com.library.service.BookService;

public class Main {

    public static void main(String[] args) {

        BookRepository bookRepo = new BookRepository();
        UserRepository userRepo = new UserRepository();

        BookService service = new BookService(bookRepo, userRepo);

        bookRepo.save(new Book("1", "Java Basics", "Author A"));
        userRepo.save(new User("u1", "Vijendra"));

        IssueBookRequest request = new IssueBookRequest("1", "u1");

        IssueBookResponse response = service.issueBook(request);

        System.out.println(response.getMessage());
    }
}