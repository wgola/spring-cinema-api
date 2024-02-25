package com.project.cinema.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.project.cinema.dto.language.LanguageReadDto;
import com.project.cinema.dto.language.LanguageWriteDto;
import com.project.cinema.model.Language;

@Mapper
public interface LanguageMapper {

    LanguageReadDto toReadDto(Language language);

    @Mapping(target = "id", ignore = true)
    Language fromWriteDto(LanguageWriteDto languageWriteDto);
}
