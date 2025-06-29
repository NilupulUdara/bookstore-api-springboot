package com.nilupul.bookstore.service.impl;

import com.nilupul.bookstore.entity.Author;
import com.nilupul.bookstore.repository.AuthorRepository;
import com.nilupul.bookstore.service.AuthorService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepo;

    @Override
    public Author createAuthor(Author author) {
        return authorRepo.save(author);
    }

    @Override
    public Author updateAuthor(Long id, Author author) {
        Author existing = authorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
        existing.setName(author.getName());
        existing.setBio(author.getBio());
        return authorRepo.save(existing);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepo.existsById(id)) {
            throw new EntityNotFoundException("Author not found");
        }
        authorRepo.deleteById(id);
    }

    @Override
    public Author getAuthorById(Long id) {
        return authorRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Author not found"));
    }

    @Override
    public List<Author> getAllAuthors() {
        return authorRepo.findAll();
    }
}
