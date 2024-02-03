package com.project.cinema.service.language;

import java.util.List;

import com.project.cinema.model.Language;

public interface LanguageService {

    List<Language> getAll();

    Language getById(Long id);

    Language save(Language language);

    Language update(Long id, Language language);

    Long delete(Long id);
}
