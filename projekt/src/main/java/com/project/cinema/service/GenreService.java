package com.project.cinema.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Genre;
import com.project.cinema.repository.GenreRepository;

@Service
public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public Genre getGenreById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre", id));
    }

    public Genre createGenre(Genre genre) {
        try {
            return genreRepository.save(genre);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Genre");
        }
    }

    public Genre updateGenre(Long id, Genre genre) {
        Genre genreToUpdate = getGenreById(id);

        genreToUpdate.update(genre);

        return createGenre(genreToUpdate);
    }

    public Long deleteGenre(Long id) {
        Genre genreToDelete = getGenreById(id);

        try {
            genreRepository.delete(genreToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Genre", id);
        }
    }
}
