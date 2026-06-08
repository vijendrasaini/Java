package com.library.service;

import com.library.dto.IssueBookRequest;
import com.library.dto.IssueBookResponse;
import com.library.exception.BookAlreadyIssuedException;
import com.library.model.Book;
import com.library.model.User;
import com.library.repository.BookRepository;
import com.library.repository.UserRepository;

public class BookService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    public BookService(BookRepository bookRepository,
                       UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    public IssueBookResponse issueBook(IssueBookRequest request) {

        Book book = bookRepository.findById(request.getBookId());
        User user = userRepository.findById(request.getUserId());

        if (book == null) {
            throw new RuntimeException("Book not found: " + request.getBookId());
        }

        if (user == null) {
            throw new RuntimeException("User not found: " + request.getUserId());
        }

        if (book.isIssued()) {
            throw new BookAlreadyIssuedException(
                "Book " + book.getId() + " already issued to " + book.getIssuedToUserId()
            );
        }

        book.issueTo(user.getId());
        bookRepository.save(book);

        return new IssueBookResponse(
            book.getId(),
            user.getId(),
            "Book issued successfully"
        );
    }
}