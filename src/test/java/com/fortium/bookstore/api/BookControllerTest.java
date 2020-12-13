package com.fortium.bookstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortium.bookstore.dto.AuthorInfo;
import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.dto.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.fortium.bookstore.api.AuthorController.AUTHOR_API_PATH;
import static com.fortium.bookstore.api.BookController.BOOK_API_PATH;
import static com.fortium.bookstore.constant.TestConstants.*;
import static com.fortium.bookstore.constant.TestConstants.DESCRIPTION;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class BookControllerTest {

    private static final ObjectMapper mapper = new ObjectMapper();

    private static final String BOOKS_PER_PAGE = "3";

    @Autowired
    protected MockMvc mvc;

    @Test
    void createAndGetBook() throws Exception {
        AuthorInfo author = new AuthorInfo();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);

        //Create a new author
        ResultActions performPost = mvc.perform(post(AUTHOR_API_PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(author)));
        AuthorInfo authorInfo = mapper.readValue(performPost.andReturn().getResponse().getContentAsString(), AuthorInfo.class);

        //Add a book to the author
        BookInfo book = new BookInfo();
        book.setTitle(TITLE);
        book.setDescription(DESCRIPTION);
        ResultActions performPut = mvc.perform(put(AUTHOR_API_PATH + "/" + authorInfo.getId() + "/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(book)));
        BookDto bookDto = mapper.readValue(performPut.andReturn().getResponse().getContentAsString(), BookDto.class);

        //Get book
        ResultActions performGet = mvc.perform(get(BOOK_API_PATH + "/" + bookDto.getId()));
        performGet
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)))
                .andExpect(jsonPath("$.author.id", is(bookDto.getAuthor().getId()), Long.class))
                .andExpect(status().isOk());
    }

    @Test
    void addBookToAuthor() throws Exception {
        AuthorInfo author = new AuthorInfo();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);

        //Create a new author
        ResultActions performPost = mvc.perform(post(AUTHOR_API_PATH)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(author)));
        performPost
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.books").doesNotExist())
                .andExpect(status().isCreated());
        AuthorInfo authorInfo = mapper.readValue(performPost.andReturn().getResponse().getContentAsString(), AuthorInfo.class);

        //Adds a book to that author
        BookInfo book = new BookInfo();
        book.setTitle(TITLE);
        book.setDescription(DESCRIPTION);
        ResultActions performPut = mvc.perform(put(AUTHOR_API_PATH + "/" + authorInfo.getId() + "/book")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(book)));
        performPut
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.title", is(TITLE)))
                .andExpect(jsonPath("$.description", is(DESCRIPTION)))
                .andExpect(status().isAccepted());

        //Get author and check the new book has been added
        ResultActions performGet = mvc.perform(get(AUTHOR_API_PATH + "/" + authorInfo.getId()));
        performGet
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.books", hasSize(1)))
                .andExpect(jsonPath("$.books[0].title", is(TITLE)))
                .andExpect(jsonPath("$.books[0].description", is(DESCRIPTION)))
                .andExpect(status().isOk());
    }

    @Test
    void paginateBooks() throws Exception {
        ResultActions performGet = mvc.perform(get(BOOK_API_PATH).param(PAGE, "1").param(LIMIT, BOOKS_PER_PAGE));
        performGet
                .andExpect(jsonPath("$", hasSize(Integer.parseInt(BOOKS_PER_PAGE))))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].title", notNullValue()))
                .andExpect(jsonPath("$[0].description", notNullValue()))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].title", notNullValue()))
                .andExpect(jsonPath("$[1].description", notNullValue()))
                .andExpect(jsonPath("$[2].id", notNullValue()))
                .andExpect(jsonPath("$[2].title", notNullValue()))
                .andExpect(jsonPath("$[2].description", notNullValue()))
                .andExpect(status().isOk());
    }
}