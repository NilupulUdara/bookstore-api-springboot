package com.nilupul.bookstore.service;

import com.nilupul.bookstore.entity.Genre;
import java.util.List;

public interface GenreService {
    Genre createGenre(Genre genre);
    Genre updateGenre(Long id, Genre genre);
    void deleteGenre(Long id);
    Genre getGenreById(Long id);
    List<Genre> getAllGenres();
}
