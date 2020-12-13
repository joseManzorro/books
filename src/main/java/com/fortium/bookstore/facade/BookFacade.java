package com.fortium.bookstore.facade;

import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFacade {

    public static final String BOOK_DOES_NOT_EXIST = "Book does not exist.";

    @Autowired
    private BookService bookService;

    private final ModelMapper mapper = new ModelMapper();

    public BookDto getBook(Long id) {
        return bookService.getBook(id)
                .map(author -> mapper.map(author, BookDto.class))
                .orElseThrow(() -> new IllegalArgumentException(BOOK_DOES_NOT_EXIST));
    }

    public List<BookDto> getBooks(Integer page, Integer limit) {
        return mapper.map(bookService.getBooks(page, limit).getContent(),
                new TypeToken<List<BookDto>>() {}.getType());
    }
}
