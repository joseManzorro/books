package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookService {

    private BookRepository bookRepository;

    public Book createBook(Book book) {
        try {
            return bookRepository.save(book);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("A book with the same title and author already exists");
        }
    }

    public Optional<Book> getBook(String id) {
        return bookRepository.findById(id);
    }
}
