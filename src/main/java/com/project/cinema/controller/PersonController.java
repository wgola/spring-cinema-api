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

import com.project.cinema.dto.genre.GenreReadDto;
import com.project.cinema.dto.person.PersonReadDto;
import com.project.cinema.dto.person.PersonUpdateDto;
import com.project.cinema.dto.person.PersonWriteDto;
import com.project.cinema.mapper.GenreMapper;
import com.project.cinema.mapper.PersonMapper;
import com.project.cinema.model.Person;
import com.project.cinema.service.genre.GenreService;
import com.project.cinema.service.person.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final GenreService genreService;
    private final PersonMapper personMapper;
    private final GenreMapper genreMapper;

    public PersonController(
            PersonService personService,
            GenreService genreService,
            PersonMapper personMapper,
            GenreMapper genreMapper) {
        this.personService = personService;
        this.genreService = genreService;
        this.personMapper = personMapper;
        this.genreMapper = genreMapper;
    }

    @GetMapping
    public List<PersonReadDto> getAllPeople() {
        return personService.getAll().stream()
                .map(personMapper::toReadDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonReadDto getPersonById(@PathVariable Long id) {
        Person foundPerson = personService.getById(id);

        return personMapper.toReadDto(foundPerson);
    }

    @GetMapping("/{id}/genre")
    public List<GenreReadDto> getPersonGenres(@PathVariable Long id) {
        Person foundPerson = personService.getById(id);

        return genreService.getPersonGenres(foundPerson.getId()).stream()
                .map(genreMapper::toReadDto)
                .toList();
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public PersonReadDto createPerson(@RequestBody @Valid PersonWriteDto person) {
        Person personToCreate = personMapper.fromWriteDto(person);
        Person createdPerson = personService.save(personToCreate);

        return personMapper.toReadDto(createdPerson);
    }

    @PutMapping("/{id}")
    public PersonReadDto updatePerson(@PathVariable Long id, @RequestBody @Valid PersonUpdateDto person) {
        Person personToUpdate = Person.builder()
                .firstName(person.firstName().orElse(null))
                .lastName(person.lastName().orElse(null))
                .dateOfBirth(person.dateOfBirth().orElse(null))
                .build();

        Person updatedPerson = personService.update(id, personToUpdate);

        return personMapper.toReadDto(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deletePerson(@PathVariable Long id) {
        return Map.of("id", personService.delete(id));
    }
}
