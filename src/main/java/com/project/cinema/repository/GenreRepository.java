package com.project.cinema.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Long> {

    Optional<Genre> findByName(String name);

    @Query("""
            SELECT g
            FROM Person p
            JOIN p.actedInMovies m
            JOIN m.genres g
            WHERE p.id = :personId
            """)
    List<Genre> getGenresByPersonId(@Param("personId") Long personId);
}
