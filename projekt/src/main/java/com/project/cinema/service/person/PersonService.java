package com.project.cinema.service.person;

import java.util.List;

import com.project.cinema.model.Person;

public interface PersonService {

    List<Person> getAll();

    Person getById(Long id);

    Person save(Person person);

    Person update(Long id, Person person);

    Long delete(Long id);
}
