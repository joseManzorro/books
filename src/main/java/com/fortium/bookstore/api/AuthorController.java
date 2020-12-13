package com.fortium.bookstore.api;

import com.fortium.bookstore.dto.AuthorDto;
import com.fortium.bookstore.dto.AuthorInfo;
import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.dto.BookInfo;
import com.fortium.bookstore.facade.AuthorFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(AuthorController.AUTHOR_API_PATH)
public class AuthorController {

    public static final String AUTHOR_API_PATH = "/authors";

    private final AuthorFacade authorFacade;

    @GetMapping("/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        return ResponseEntity.ok(authorFacade.getAuthor(id));
    }

    @PostMapping
    public ResponseEntity<AuthorInfo> createAuthor(@RequestBody @Valid AuthorInfo author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authorFacade.saveAuthor(author));
    }

    @PutMapping("/{authorId}/book")
    public ResponseEntity<BookDto> addBookToAuthor(@RequestBody @Valid BookInfo book, @PathVariable Long authorId) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authorFacade.addBookToAuthor(book, authorId));
    }

    @GetMapping
    public List<AuthorDto> getAuthors(@RequestParam Integer page, @RequestParam @Min(1) Integer limit) {
        return authorFacade.getAuthors(page, limit);
    }

}
