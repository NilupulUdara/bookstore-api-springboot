package com.nilupul.bookstore.service.impl;

import com.nilupul.bookstore.entity.Genre;
import com.nilupul.bookstore.repository.GenreRepository;
import com.nilupul.bookstore.service.GenreService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepo;

    @Override
    public Genre createGenre(Genre genre) {
        return genreRepo.save(genre);
    }

    @Override
    public Genre updateGenre(Long id, Genre genre) {
        Genre existing = genreRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre not found"));
        existing.setName(genre.getName());
        existing.setDescription(genre.getDescription());
        return genreRepo.save(existing);
    }

    @Override
    public void deleteGenre(Long id) {
        if (!genreRepo.existsById(id)) {
            throw new EntityNotFoundException("Genre not found");
        }
        genreRepo.deleteById(id);
    }

    @Override
    public Genre getGenreById(Long id) {
        return genreRepo.findById(id).orElseThrow(() -> new EntityNotFoundException("Genre not found"));
    }

    @Override
    public List<Genre> getAllGenres() {
        return genreRepo.findAll();
    }
}
