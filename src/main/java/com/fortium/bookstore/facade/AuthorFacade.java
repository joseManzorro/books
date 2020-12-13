package com.fortium.bookstore.facade;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.dto.AuthorDto;
import com.fortium.bookstore.dto.AuthorInfo;
import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.dto.BookInfo;
import com.fortium.bookstore.service.AuthorService;
import com.fortium.bookstore.service.BookService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorFacade {

    public static final String AUTHOR_DOES_NOT_EXIST = "Author does not exist";

    @Autowired
    private AuthorService authorService;

    @Autowired
    private BookService bookService;

    private final ModelMapper mapper = new ModelMapper();

    public AuthorDto getAuthor(Long id) {
        return authorService.getAuthor(id)
                .map(author -> mapper.map(author, AuthorDto.class))
                .orElseThrow(() -> new IllegalArgumentException(AUTHOR_DOES_NOT_EXIST));
    }

    public AuthorInfo saveAuthor(AuthorInfo author) {
        return mapper.map(authorService.saveAuthor(mapper.map(author, Author.class)), AuthorInfo.class);
    }

    /**
     * Adds book to existing author.
     * Throws IllegalArgumentException if author does not exist.
     *
     * @param book
     * @param authorId
     */
    public BookDto addBookToAuthor(BookInfo book, Long authorId) {
        Author author = authorService.getAuthor(authorId)
                .orElseThrow(() -> new IllegalArgumentException(AUTHOR_DOES_NOT_EXIST));
        return mapper.map(bookService.addBookToAuthor(mapper.map(book, Book.class), author), BookDto.class);
    }

    public List<AuthorDto> getAuthors(Integer page, Integer limit) {
        return mapper.map(authorService.getAuthors(page, limit).getContent(),
                new TypeToken<List<AuthorDto>>() {}.getType());
    }
}
