package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.domain.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.util.HashSet;

import static com.fortium.bookstore.constant.TestConstants.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private AuthorService authorService;

    @Test
    void addBookToExistingAuthor() {
        Author author = new Author();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);
        author.setBooks(new HashSet<>());
        Author persistedAuthor = authorService.saveAuthor(author);

        Book book = new Book();
        book.setTitle(TITLE);
        book.setDescription(DESCRIPTION);
        Book persistedBook = bookService.addBookToAuthor(book, persistedAuthor);

        assertThat(persistedBook.getId()).isNotNull();
        assertThat(persistedBook.getTitle()).isEqualTo(TITLE);
        assertThat(persistedBook.getDescription()).isEqualTo(DESCRIPTION);
        assertThat(persistedBook.getAuthor().getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(persistedBook.getAuthor().getLastName()).isEqualTo(LAST_NAME);

        assertThat(persistedAuthor.getId()).isNotNull();
        assertThat(persistedAuthor.getBooks()).contains(persistedBook);
    }
}