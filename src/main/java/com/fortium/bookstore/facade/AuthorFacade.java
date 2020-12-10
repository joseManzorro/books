package com.fortium.bookstore.facade;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.domain.Book;
import com.fortium.bookstore.dto.AuthorDto;
import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.service.AuthorService;
import lombok.AllArgsConstructor;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorFacade {

    private ModelMapper mapper;
    private AuthorService authorService;

    public Optional<AuthorDto> getAuthor(String id) {
        return authorService.getAuthor(id)
                .map((author) -> mapper.map(author, AuthorDto.class));
    }

    public AuthorDto createAuthor(AuthorDto author) {
        try {
            return mapper.map(authorService.createAuthor(mapper.map(author, Author.class)), AuthorDto.class);
        } catch (MappingException e) {
            int n = 0;
        }
        return null;
    }
}
