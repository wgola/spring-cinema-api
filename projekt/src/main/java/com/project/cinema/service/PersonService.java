package com.project.cinema.service;

import org.springframework.stereotype.Service;

import com.project.cinema.repository.PersonRepository;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
}
