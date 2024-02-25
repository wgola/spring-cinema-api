package com.project.cinema.controller;

import static org.springframework.http.HttpStatus.CREATED;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.cinema.dto.language.LanguageReadDto;
import com.project.cinema.dto.language.LanguageWriteDto;
import com.project.cinema.mapper.LanguageMapper;
import com.project.cinema.model.Language;
import com.project.cinema.service.language.LanguageService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/language")
public class LanguageController {

    private final LanguageService languageService;
    private final LanguageMapper languageMapper;

    public LanguageController(LanguageService languageService, LanguageMapper languageMapper) {
        this.languageService = languageService;
        this.languageMapper = languageMapper;
    }

    @GetMapping
    public List<LanguageReadDto> getAllLanguages() {
        return languageService.getAll().stream()
                .map(languageMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public LanguageReadDto getLanguageById(@PathVariable Long id) {
        Language foundLanguage = languageService.getById(id);

        return languageMapper.toReadDto(foundLanguage);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public LanguageReadDto createLanguage(@RequestBody @Valid LanguageWriteDto language) {
        Language languageToCreate = languageMapper.fromWriteDto(language);
        Language createdLanguage = languageService.save(languageToCreate);

        return languageMapper.toReadDto(createdLanguage);
    }

    @PutMapping("/{id}")
    public LanguageReadDto updateLanguage(@PathVariable Long id, @RequestBody @Valid LanguageWriteDto language) {
        Language languageToUpdate = languageMapper.fromWriteDto(language);
        Language updatedLanguage = languageService.update(id, languageToUpdate);

        return languageMapper.toReadDto(updatedLanguage);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deleteLanguage(@PathVariable Long id) {
        return Map.of("id", languageService.delete(id));
    }
}
