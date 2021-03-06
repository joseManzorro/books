package com.fortium.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
public class BookDto {

    private Long id;
    @NotBlank(message = "A book should have a title")
    private String title;
    private String description;
    private AuthorInfo author;
}
