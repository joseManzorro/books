package com.fortium.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDto {

    private String id;
    @NotBlank(message = "A book should have a title")
    private String title;
    private String description;
    @Valid
    @NotNull(message = "A book should have an author")
    private AuthorDto author;
}
