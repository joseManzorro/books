package com.fortium.bookstore.api;

import com.fortium.bookstore.dto.AuthorDto;
import com.fortium.bookstore.facade.AuthorFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/authors")
public class AuthorController {

    private AuthorFacade authorFacade;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable String id) {
        //TODO Create custom error response with timestamp and message
        Optional<AuthorDto> authorDto = authorFacade.getAuthor(id);
        return authorDto.isPresent() ? ResponseEntity.ok(authorDto.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody @Valid AuthorDto author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorFacade.createAuthor(author));
    }

}
