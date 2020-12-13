package com.fortium.bookstore.api;

import com.fortium.bookstore.dto.BookDto;
import com.fortium.bookstore.facade.BookFacade;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(BookController.BOOK_API_PATH)
public class BookController {

    public static final String BOOK_API_PATH = "/books";

    private final BookFacade bookFacade;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable Long id) {
        return ResponseEntity.ok(bookFacade.getBook(id));
    }

    @GetMapping
    public List<BookDto> getBooks(@RequestParam Integer page, @RequestParam @Min(1) Integer limit) {
        return bookFacade.getBooks(page, limit);
    }

}
