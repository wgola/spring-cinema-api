package com.project.cinema.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.cinema.model.Language;

@Repository
public interface LanguageRepository extends JpaRepository<Language, Long> {
}
