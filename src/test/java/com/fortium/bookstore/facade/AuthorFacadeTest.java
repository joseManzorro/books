package com.fortium.bookstore.facade;

import com.fortium.bookstore.dto.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static com.fortium.bookstore.constant.TestConstants.DESCRIPTION;
import static com.fortium.bookstore.constant.TestConstants.TITLE;
import static com.fortium.bookstore.facade.AuthorFacade.AUTHOR_DOES_NOT_EXIST;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class AuthorFacadeTest {

    @Autowired
    private AuthorFacade authorFacade;

    @Test
    void addBookToNonExistingAuthor() {
        BookInfo book = new BookInfo();
        book.setTitle(TITLE);
        book.setDescription(DESCRIPTION);
        assertThatThrownBy(() -> authorFacade.addBookToAuthor(book, -1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AUTHOR_DOES_NOT_EXIST);
    }

    @Test
    void getNonExistingAuthor() {
        BookInfo book = new BookInfo();
        book.setTitle(TITLE);
        book.setDescription(DESCRIPTION);

        assertThatThrownBy(() -> authorFacade.getAuthor(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(AUTHOR_DOES_NOT_EXIST);
    }
}