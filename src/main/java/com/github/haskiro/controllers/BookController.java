package com.github.haskiro.controllers;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import com.github.haskiro.services.BooksService;
import com.github.haskiro.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BookController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BookController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }


    @GetMapping()
    public String index(Model model) {
        model.addAttribute("books", booksService.findAll());
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

        booksService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/{id}")
    public String singleBook(@PathVariable("id") int id,
                               Model model) {
        Book book = booksService.findOneById(id);
        Person person = book.getOwner();

        if (book.getOwner() == null) {
            model.addAttribute("people", peopleService.findAll());
            person = new Person();
        }

        model.addAttribute("person", person);
        model.addAttribute("book", book);

        return "books/singleBook";
    }

    @PatchMapping("/{id}")
    public String updateBook(@PathVariable("id") int id, @ModelAttribute("book") Book book) {
        booksService.update(id, book);

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/set_owner")
    public String setBookOwner(@PathVariable("id") int id, @ModelAttribute("person") Person person) {
        booksService.setOwner(id, person.getId());

        return "redirect:/books/" + id;
    }

    @PatchMapping("/{id}/unset_owner")
    public String unsetBookOwner(@PathVariable("id") int id) {
        booksService.unsetOwner(id);

        return "redirect:/books/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        booksService.delete(id);

        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String editBook(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", booksService.findOneById(id));

        return "books/edit";
    }

}
