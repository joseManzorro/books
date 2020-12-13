package com.fortium.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Book information excluding author to avoid infinite recursion when mapping from Author to AuthorDto
 */
@Getter
@Setter
@NoArgsConstructor
public class BookInfo {
    private Long id;
    @NotBlank(message = "A book should have a title")
    private String title;
    private String description;
}
