package com.nilupul.bookstore.mapper;

import com.nilupul.bookstore.dto.book.BookRequestDTO;
import com.nilupul.bookstore.dto.book.BookResponseDTO;
import com.nilupul.bookstore.entity.Book;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface BookMapper {

    @Mapping(source = "author.name", target = "authorName")
    @Mapping(source = "genre.name", target = "genreName")
    BookResponseDTO toDTO(Book book);

    @Mapping(target = "author", ignore = true) // We set author manually in service
    @Mapping(target = "genre", ignore = true)  // Same for genre
    Book toEntity(BookRequestDTO dto);
}
