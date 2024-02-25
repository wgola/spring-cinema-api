package com.project.cinema.service.genre;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Genre;
import com.project.cinema.repository.GenreRepository;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> getAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre getById(Long id) {
        return genreRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Genre", id));
    }

    @Override
    public Genre save(Genre genre) {
        try {
            return genreRepository.save(genre);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Genre", e.getClass().getSimpleName());
        }
    }

    @Override
    public Genre update(Long id, Genre genre) {
        Genre genreToUpdate = getById(id);

        genreToUpdate.update(genre);

        return save(genreToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Genre genreToDelete = getById(id);

        try {
            genreRepository.delete(genreToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Genre", id);
        }
    }

    @Override
    public List<Genre> getPersonGenres(Long personId) {
        return genreRepository.getGenresByPersonId(personId);
    }
}
