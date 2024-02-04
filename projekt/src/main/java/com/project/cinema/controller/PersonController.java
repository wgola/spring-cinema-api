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

import com.project.cinema.dto.person.PersonReadDto;
import com.project.cinema.dto.person.PersonUpdateDto;
import com.project.cinema.dto.person.PersonWriteDto;
import com.project.cinema.mapper.PersonMapper;
import com.project.cinema.model.Person;
import com.project.cinema.service.person.PersonService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/person")
public class PersonController {

    private final PersonService personService;
    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    @GetMapping
    public List<PersonReadDto> getAllPeople() {
        return personService.getAll().stream()
                .map(personMapper::toReadtDto)
                .toList();
    }

    @GetMapping("/{id}")
    public PersonReadDto getPersonById(@PathVariable Long id) {
        Person foundPerson = personService.getById(id);

        return personMapper.toReadtDto(foundPerson);
    }

    @PostMapping
    @ResponseStatus(code = CREATED)
    public PersonReadDto createPerson(@RequestBody @Valid PersonWriteDto person) {
        Person personToCreate = personMapper.fromWriteDto(person);
        Person createdPerson = personService.save(personToCreate);

        return personMapper.toReadtDto(createdPerson);
    }

    @PutMapping("/{id}")
    public PersonReadDto updatePerson(@PathVariable Long id, @RequestBody @Valid PersonUpdateDto person) {
        Person personToUpdate = Person.builder()
                .firstName(person.firstName().orElse(null))
                .lastName(person.lastName().orElse(null))
                .dateOfBirth(person.dateOfBirth().orElse(null))
                .build();

        Person updatedPerson = personService.update(id, personToUpdate);

        return personMapper.toReadtDto(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public Map<String, Long> deletePerson(@PathVariable Long id) {
        return Map.of("id", personService.delete(id));
    }
}
