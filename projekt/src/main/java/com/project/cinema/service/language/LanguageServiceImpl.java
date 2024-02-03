package com.project.cinema.service.language;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Language;
import com.project.cinema.repository.LanguageRepository;

@Service
public class LanguageServiceImpl implements LanguageService {

    private final LanguageRepository languageRepository;

    public LanguageServiceImpl(LanguageRepository languageRepository) {
        this.languageRepository = languageRepository;
    }

    @Override
    public List<Language> getAll() {
        return languageRepository.findAll();
    }

    @Override
    public Language getById(Long id) {
        return languageRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Language", id));
    }

    @Override
    public Language save(Language language) {
        try {
            return languageRepository.save(language);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Language", e.getClass().getSimpleName());
        }
    }

    @Override
    public Language update(Long id, Language language) {
        Language languageToUpdate = getById(id);

        languageToUpdate.update(language);

        return save(languageToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Language languageToDelete = getById(id);

        try {
            languageRepository.delete(languageToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Language", id);
        }
    }
}
