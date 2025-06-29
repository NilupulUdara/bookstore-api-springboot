package com.nilupul.bookstore.service;

import com.nilupul.bookstore.entity.Author;
import java.util.List;

public interface AuthorService {
    Author createAuthor(Author author);
    Author updateAuthor(Long id, Author author);
    void deleteAuthor(Long id);
    Author getAuthorById(Long id);
    List<Author> getAllAuthors();
}
