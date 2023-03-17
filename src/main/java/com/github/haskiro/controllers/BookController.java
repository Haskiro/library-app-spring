package com.github.haskiro.controllers;

import com.github.haskiro.models.Book;
import com.github.haskiro.models.Person;
import com.github.haskiro.services.BooksService;
import com.github.haskiro.services.PeopleService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public String index(Model model, @RequestParam(value = "sort_by_year") Optional<Boolean> sortByYear,
                        @RequestParam(value = "page") Optional<Integer> page, @RequestParam(value = "books_per_page") Optional<Integer> booksPerPage) {
        List<Book> books = new ArrayList<>();
        if (page.isPresent() && booksPerPage.isPresent()) {
            if (sortByYear.isPresent()) {
                books.addAll(booksService.findAll(page.get(), booksPerPage.get(), sortByYear.get()));
            } else {
                books.addAll(booksService.findAll(page.get(), booksPerPage.get(), false));
            }
        } else if (sortByYear.isPresent()) {
            books.addAll(booksService.findAll(sortByYear.get()));
        } else {
            books.addAll(booksService.findAll());
        }

        model.addAttribute("books", books);
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

    @GetMapping("/search")
    public String search(@RequestParam("value") Optional<String> value, Model model) {
        if (value.isEmpty()) {
            model.addAttribute("isSearched", false);
            model.addAttribute("value", "");
            return "books/search";
        }

        List<Book> books = booksService.search(value.get());
        model.addAttribute("isSearched", true);
        model.addAttribute("books", books);
        model.addAttribute("value", value.get());

        return "books/search";
    }

}
