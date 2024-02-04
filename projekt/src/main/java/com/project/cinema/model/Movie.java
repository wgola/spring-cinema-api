package com.project.cinema.model;

import static jakarta.persistence.CascadeType.PERSIST;
import static jakarta.persistence.FetchType.LAZY;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Movie extends AbstractEntity {

    @Column(length = 30, nullable = false)
    private String title;

    @Column(length = 255, nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @ManyToMany(fetch = LAZY, cascade = PERSIST)
    private List<Genre> genres;

    @ManyToOne(fetch = LAZY, cascade = PERSIST)
    private Language language;

    @ManyToMany(fetch = LAZY)
    private List<Person> actors;

    @ManyToMany(fetch = LAZY)
    private List<Person> directors;

    @Override
    public void update(AbstractEntity other) {
        if (other instanceof Movie m) {

            if (m.title != null && m.title != title) {
                title = m.title;
            }

            if (m.description != null && m.description != description) {
                description = m.description;
            }

            if (m.releaseDate != null && m.releaseDate != releaseDate) {
                releaseDate = m.releaseDate;
            }

            if (m.genres != null && m.genres.size() != 0) {
                genres = m.genres;
            }

            if (m.language != null) {
                language = m.language;
            }

            if (m.actors != null && m.actors.size() != 0) {
                actors = m.actors;
            }

            if (m.directors != null && m.directors.size() != 0) {
                directors = m.directors;
            }
        }
    }
}
