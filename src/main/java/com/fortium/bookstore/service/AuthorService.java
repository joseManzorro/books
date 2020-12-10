package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author createAuthor(Author author) {
        try {
            return authorRepository.save(author);
        } catch (DuplicateKeyException e) {
            throw new DuplicateKeyException("An author with the same first name and last name already exists");
        }
    }

    public Optional<Author> getAuthor(String id) {
        return authorRepository.findById(id);
    }
}
