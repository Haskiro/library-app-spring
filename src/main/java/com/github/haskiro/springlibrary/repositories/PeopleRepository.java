package com.github.haskiro.springlibrary.repositories;

import com.github.haskiro.springlibrary.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<Person> findOneByFullName(String fullName);
}
