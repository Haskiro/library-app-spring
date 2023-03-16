package com.github.haskiro.services;

import com.github.haskiro.models.Person;
import com.github.haskiro.repositories.PeopleRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Optional<Person> findOneById(int id) {
        Optional<Person> person = peopleRepository.findById(id);
        person.ifPresent(value -> Hibernate.initialize(value.getBooks()));

        return peopleRepository.findById(id);
    }

    public Optional<Person> findOneByFullName(String fullName) {
        return peopleRepository.findOneByFullName(fullName);
    }

    @Transactional
    public void create(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }






}
