package com.github.haskiro.services;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import com.github.haskiro.repositories.BooksRepository;
import com.github.haskiro.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.OffsetDateTime;
import java.util.*;

@Service
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findOneById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void setOwner(int id, int person_id) {
        Person person = peopleRepository.getReferenceById(person_id);
        booksRepository.setOwner(id, person, OffsetDateTime.now());
    }

    @Transactional
    public void unsetOwner(int id) {
        booksRepository.unsetOwner(id);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    @Transactional
    public void create(Book book) {
        booksRepository.save(book);
    }
}
