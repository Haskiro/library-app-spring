package com.github.haskiro.springlibrary.services;

import com.github.haskiro.springlibrary.models.Book;
import com.github.haskiro.springlibrary.models.Person;
import com.github.haskiro.springlibrary.repositories.BooksRepository;
import com.github.haskiro.springlibrary.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.OffsetDateTime;
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

    public List<Book> findAllWithPagination(int page, int itemsPerPage, boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        }

        return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }

    public List<Book> findAll(boolean sortByYear) {
        if (sortByYear) {
            return booksRepository.findAll(Sort.by("year"));
        }

        return booksRepository.findAll();
    }

    public Book findOneById(int id) {
        Optional<Book> book = booksRepository.findById(id);
        return book.orElse(null);
    }

    @Transactional
    public void update(int id, Book updatedBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();

        updatedBook.setOwner(bookToBeUpdated.getOwner()); // Чтоб не потерялась связь при обновлении
        updatedBook.setId(id);
        // Добавляем по сути новую книгу (которая не находится в Persistence context), поэтму нужуен save()
        booksRepository.save(updatedBook);
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

    public List<Book> search(String value) {
        return booksRepository.findByTitleContainingIgnoreCase(value);
    }
}
