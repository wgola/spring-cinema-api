package com.project.cinema.service.genre;

import java.util.List;

import com.project.cinema.model.Genre;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(Long id);

    Genre save(Genre genre);

    Genre update(Long id, Genre genre);

    Long delete(Long id);
}
