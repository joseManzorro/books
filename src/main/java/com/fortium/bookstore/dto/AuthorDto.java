package com.fortium.bookstore.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AuthorDto {

    private String id;
    @NotBlank(message = "An author should have first name")
    private String firstName;
    private String lastName;
    private Set<BookDto> books;
}
