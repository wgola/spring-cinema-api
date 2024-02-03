package com.project.cinema.service.person;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.cinema.exception.EntityNotFoundException;
import com.project.cinema.exception.ErrorCreatingEntityException;
import com.project.cinema.exception.ErrorDeletingEntityException;
import com.project.cinema.model.Person;
import com.project.cinema.repository.PersonRepository;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Override
    public List<Person> getAll() {
        return personRepository.findAll();
    }

    @Override
    public Person getById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person", id));
    }

    @Override
    public Person save(Person person) {
        try {
            return personRepository.save(person);
        } catch (Exception e) {
            throw new ErrorCreatingEntityException("Person", e.getClass().getSimpleName());
        }
    }

    @Override
    public Person update(Long id, Person person) {
        Person personToUpdate = getById(id);

        personToUpdate.update(person);

        return save(personToUpdate);
    }

    @Override
    public Long delete(Long id) {
        Person personToDelete = getById(id);

        try {
            personRepository.delete(personToDelete);
            return id;
        } catch (Exception e) {
            throw new ErrorDeletingEntityException("Person", id);
        }
    }
}
