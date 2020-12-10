package com.fortium.bookstore.api;

import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.facade.BookFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/books")
public class BookController {

    private BookFacade bookFacade;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable String id) {
        //TODO Create custom error response with timestamp and message
        Optional<BookDto> book = bookFacade.getBook(id);
        return book.isPresent() ? ResponseEntity.ok(book.get())
                : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BookDto> createBook(@RequestBody @Valid BookDto book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookFacade.createBook(book));
    }

}
