package com.fortium.bookstore.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

/**
 * Book entity.
 * For every book, title and author id pairs should be unique.
 * An author should not have two books with the same title.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(indexes = @Index(name = "titleAuthorIndex", columnList = "title, author_id", unique = true))
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Author author;
}
