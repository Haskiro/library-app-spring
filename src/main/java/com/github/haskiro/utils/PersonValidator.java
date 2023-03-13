package com.github.haskiro.utils;

import com.github.haskiro.dao.PersonDAO;
import com.github.haskiro.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.swing.text.html.Option;
import java.util.Optional;

@Component
public class PersonValidator implements Validator {
    private final PersonDAO personDAO;

    @Autowired
    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Person.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Person person = (Person) target;
        Optional<Person> existingPerson = personDAO.getSinglePerson(person.getFullName());

        if (existingPerson.isPresent() && !existingPerson.get().getFullName().equals(person.getFullName())) {
            errors.rejectValue("fullName", "", "Это ФИО уже занято");
        }
    }
}
