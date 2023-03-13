package com.github.haskiro.controllers;

import com.github.haskiro.dao.BookDAO;
import com.github.haskiro.dao.PersonDAO;
import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BookController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }

    @GetMapping("/new")
    public String newBook(@ModelAttribute("book") Book book) {
        return "books/new";
    }

    @PostMapping()
    public String createBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "books/new";
        }

        bookDAO.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String singlePerson(@PathVariable("id") int id,
                               Model model) {
        Book book = bookDAO.getSingleBook(id).orElse(null);

        if (book.getPersonId() == null) {
            model.addAttribute("people", personDAO.index());
        } else {
            model.addAttribute("person", personDAO.getSinglePerson(book.getPersonId()).get());
        }
        model.addAttribute("book", book);

        return "books/singleBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        System.out.println(book.getName());
        bookDAO.update(id, book);

        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.getSingleBook(id).orElse(null));

        return "books/edit";
    }

}
