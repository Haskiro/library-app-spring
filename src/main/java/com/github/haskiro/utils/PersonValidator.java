package com.github.haskiro.utils;

import com.github.haskiro.models.Person;
import com.github.haskiro.services.PeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PeopleService peopleService;

    @Autowired
    public PersonValidator(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> existingPerson = peopleService.findOneByFullName(person.getFullName());

        if (existingPerson.isPresent() && !existingPerson.get().getFullName().equals(person.getFullName())) {
            errors.rejectValue("fullName", "", "Это ФИО уже занято");
        }
    }
}
