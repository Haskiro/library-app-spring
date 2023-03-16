package com.github.haskiro.services;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import com.github.haskiro.repositories.BooksRepository;
import com.github.haskiro.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(int id, Book book) {
        book.setId(id);
        booksRepository.save(book);
    }

    @Transactional
    public void setOwner(int id, int person_id) {
        Person person = peopleRepository.getReferenceById(person_id);
        booksRepository.setOwner(person, id);
    }

    @Transactional
    public void unsetOwner(int id) {
        booksRepository.setOwner(null, id);
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
