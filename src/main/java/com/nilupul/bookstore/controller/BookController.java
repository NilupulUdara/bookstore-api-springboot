package com.nilupul.bookstore.controller;

import com.nilupul.bookstore.dto.book.BookRequestDTO;
import com.nilupul.bookstore.dto.book.BookResponseDTO;
import com.nilupul.bookstore.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    // Create a new book
    @PostMapping
    public ResponseEntity<BookResponseDTO> createBook(@Valid @RequestBody BookRequestDTO dto) {
        BookResponseDTO createdBook = bookService.createBook(dto);
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Get paginated list of books (optional search by title)
    @GetMapping
    public Page<BookResponseDTO> listBooks(
            @RequestParam(required = false) String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return bookService.getAllBooks(title, page, size);
    }

    // Get book by id
    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBook(@PathVariable Long id) {
        BookResponseDTO book = bookService.getBook(id);
        return ResponseEntity.ok(book);
    }

    // Update book by id
    @PutMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @Valid @RequestBody BookRequestDTO dto) {
        BookResponseDTO updatedBook = bookService.updateBook(id, dto);
        return ResponseEntity.ok(updatedBook);
    }

    // Delete book by id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
