package com.nilupul.bookstore.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.nilupul.bookstore.dto.book.BookRequestDTO;
import com.nilupul.bookstore.dto.book.BookResponseDTO;
import com.nilupul.bookstore.entity.Author;
import com.nilupul.bookstore.entity.Book;
import com.nilupul.bookstore.entity.Genre;
import com.nilupul.bookstore.mapper.BookMapper;
import com.nilupul.bookstore.repository.AuthorRepository;
import com.nilupul.bookstore.repository.BookRepository;
import com.nilupul.bookstore.repository.GenreRepository;
import com.nilupul.bookstore.service.BookService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final GenreRepository genreRepository;
    private final BookMapper bookMapper;

    @Override
    public BookResponseDTO createBook(BookRequestDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        Book book = bookMapper.toEntity(dto);
        book.setAuthor(author);
        book.setGenre(genre);

        Book savedBook = bookRepository.save(book);
        return bookMapper.toDTO(savedBook);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookRequestDTO dto) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new RuntimeException("Author not found"));
        Genre genre = genreRepository.findById(dto.getGenreId())
                .orElseThrow(() -> new RuntimeException("Genre not found"));

        existingBook.setTitle(dto.getTitle());
        existingBook.setDescription(dto.getDescription());
        existingBook.setPrice(dto.getPrice());
        existingBook.setPublishedDate(dto.getPublishedDate());
        existingBook.setAuthor(author);
        existingBook.setGenre(genre);

        Book updatedBook = bookRepository.save(existingBook);
        return bookMapper.toDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public BookResponseDTO getBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return bookMapper.toDTO(book);
    }

    @Override
    public Page<BookResponseDTO> getAllBooks(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage;

        if (title != null && !title.isEmpty()) {
            bookPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            bookPage = bookRepository.findAll(pageable);
        }

        return bookPage.map(bookMapper::toDTO);
    }

    @Override
    public Page<BookResponseDTO> listBooks(String title, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Book> bookPage;

        if (title != null && !title.isEmpty()) {
            bookPage = bookRepository.findByTitleContainingIgnoreCase(title, pageable);
        } else {
            bookPage = bookRepository.findAll(pageable);
        }

        return bookPage.map(bookMapper::toDTO);
    }
}
