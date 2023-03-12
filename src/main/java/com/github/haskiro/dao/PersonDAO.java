package com.github.haskiro.dao;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;


    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM Person", new BeanPropertyRowMapper<>(Person.class));
    }

    public Person getSinglePerson(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE person_id=?", new Object[]{id} , new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(null);
    }

    public Optional<Person> getSinglePerson(String fullName) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE full_name=?",
                new Object[]{fullName} ,
                new BeanPropertyRowMapper<>(Person.class)).stream().findAny();
    }

    public List<Book> getSinglePersonTakenBooks(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE person_id=?", new Object[]{id}, new BookMapper());
    }

    public void create(Person person) {
        jdbcTemplate.update("INSERT INTO Person(full_name, year_of_birth) values (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }
}
