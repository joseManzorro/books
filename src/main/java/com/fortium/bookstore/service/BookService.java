package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@AllArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> getBook(Long id) {
        return bookRepository.findById(id);
    }

    public Page<Book> getBooks(Integer page, Integer limit) {
        return bookRepository.findAll(PageRequest.of(page, limit, Sort.by(asc("id"))));
    }

    public Book addBookToAuthor(Book book, Author author) {
        book.setAuthor(author);
        if (Objects.isNull(author.getBooks())) {
            author.setBooks(new HashSet<>());
        }
        author.getBooks().add(book);
        return saveBook(book);
    }
}
