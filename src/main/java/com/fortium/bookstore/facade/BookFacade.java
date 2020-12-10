package com.fortium.bookstore.facade;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.dto.AuthorDto;
import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.service.BookService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BookFacade {

    private ModelMapper mapper;
    private BookService bookService;

    public Optional<BookDto> getBook(String id) {
        return bookService.getBook(id)
                .map((book) -> mapper.map(book, BookDto.class));
    }

    public BookDto createBook(BookDto book) {
        return mapper.map(bookService.createBook(mapper.map(book, Book.class)), BookDto.class);

    }
}
