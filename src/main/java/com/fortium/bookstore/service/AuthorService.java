package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        //Used by POST: If author exists, it should not override it
        return authorRepository.insert(author);
    }

    public Optional<Author> getAuthor(String id) {
        return authorRepository.findById(id);
    }
}
