package com.nilupul.bookstore.service;

import com.nilupul.bookstore.dto.book.BookRequestDTO;
import com.nilupul.bookstore.dto.book.BookResponseDTO;
import org.springframework.data.domain.Page;

public interface BookService {
    BookResponseDTO createBook(BookRequestDTO dto);
    BookResponseDTO updateBook(Long id, BookRequestDTO dto);
    void deleteBook(Long id);
    BookResponseDTO getBook(Long id);
    Page<BookResponseDTO> listBooks(String title, int page, int size);
    Page<BookResponseDTO> getAllBooks(String title, int page, int size);
}
