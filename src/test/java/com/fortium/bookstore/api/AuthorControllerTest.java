package com.fortium.bookstore.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fortium.bookstore.dto.AuthorInfo;
import com.fortium.bookstore.dto.BookInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static com.fortium.bookstore.api.AuthorController.AUTHOR_API_PATH;
import static com.fortium.bookstore.constant.TestConstants.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerTest {

    private static final String AUTHORS_PER_PAGE = "2";

    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private MockMvc mvc;

    @Test
    void createAndGetAuthor() throws Exception {
        AuthorInfo author = new AuthorInfo();
        author.setFirstName(FIRST_NAME);
        author.setLastName(LAST_NAME);
        String requestBody = mapper.writeValueAsString(author);

        //Create a new author
        ResultActions performPost = mvc.perform(post(AUTHOR_API_PATH)
                .with(csrf().asHeader())
                .with(user("admin").password("admin").roles("ADMIN"))
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(requestBody));
        performPost
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.books").doesNotExist())
                .andExpect(status().isCreated());
        AuthorInfo authorInfo = mapper.readValue(performPost.andReturn().getResponse().getContentAsString(), AuthorInfo.class);

        ResultActions performGet = mvc.perform(get(AUTHOR_API_PATH + "/" + authorInfo.getId())
                .with(user("admin").password("admin").roles("ADMIN")));
        performGet
                .andExpect(jsonPath("$.id", notNullValue()))
                .andExpect(jsonPath("$.firstName", is(FIRST_NAME)))
                .andExpect(jsonPath("$.lastName", is(LAST_NAME)))
                .andExpect(jsonPath("$.books").isEmpty())
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
    void paginateAuthors() throws Exception {
        ResultActions performGet = mvc.perform(get(AUTHOR_API_PATH).param(PAGE, "0").param(LIMIT, AUTHORS_PER_PAGE));
        performGet
                .andExpect(jsonPath("$", hasSize(Integer.parseInt(AUTHORS_PER_PAGE))))
                .andExpect(jsonPath("$[0].id", notNullValue()))
                .andExpect(jsonPath("$[0].firstName", notNullValue()))
                .andExpect(jsonPath("$[0].lastName", notNullValue()))
                .andExpect(jsonPath("$[1].id", notNullValue()))
                .andExpect(jsonPath("$[1].firstName", notNullValue()))
                .andExpect(jsonPath("$[1].lastName", notNullValue()))
                .andExpect(status().isOk());
    }
}