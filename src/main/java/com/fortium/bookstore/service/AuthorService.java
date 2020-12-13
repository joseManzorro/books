package com.fortium.bookstore.service;

import com.fortium.bookstore.domain.Author;
import com.fortium.bookstore.repository.AuthorRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.asc;

@Service
@AllArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getAuthor(Long id) {
        return authorRepository.findById(id);
    }

    public Page<Author> getAuthors(Integer page, Integer limit) {
        return authorRepository.findAll(PageRequest.of(page, limit, Sort.by(asc("id"))));
    }
}
