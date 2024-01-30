package com.project.cinema.service;

import org.springframework.stereotype.Service;

import com.project.cinema.repository.LanguageRepository;

@Service
public class LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageService(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }
}
