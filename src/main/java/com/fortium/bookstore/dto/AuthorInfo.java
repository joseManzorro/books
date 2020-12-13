package com.fortium.bookstore.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

/**
 * Author information excluding books to avoid infinite recursion when mapping from Book to BookDto
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthorInfo {
    private Long id;
    @NotBlank(message = "An author should have first name")
    private String firstName;
    private String lastName;
}
