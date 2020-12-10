package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book createBook(Book book) {
        //Used by POST: If author exists, it should not override it
        return bookRepository.insert(book);
    }

    public Optional<Book> getBook(String id) {
        return bookRepository.findById(id);
    }
}
