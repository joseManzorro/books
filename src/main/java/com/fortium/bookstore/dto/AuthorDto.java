package com.fortium.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class AuthorDto {

    private Long id;
    @NotBlank(message = "An author should have first name")
    private String firstName;
    private String lastName;
    private Set<BookInfo> books;
}
