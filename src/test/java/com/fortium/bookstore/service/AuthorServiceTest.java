package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static com.fortium.bookstore.constant.TestConstants.FIRST_NAME;
import static com.fortium.bookstore.constant.TestConstants.LAST_NAME;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class AuthorServiceTest {

    @Autowired
    private AuthorService authorService;

    @Test
    void createAndGetAuthor() {
        Author author = new Author();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);

        Author persistedAuthor = authorService.saveAuthor(author);
        assertThat(persistedAuthor.getFirstName()).isEqualTo(FIRST_NAME);
        assertThat(persistedAuthor.getLastName()).isEqualTo(LAST_NAME);
        assertThat(persistedAuthor.getBooks()).isNull();
    }
}