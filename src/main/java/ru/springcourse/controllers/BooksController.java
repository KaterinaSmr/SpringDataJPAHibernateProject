package ru.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;
import ru.springcourse.services.BooksService;
import ru.springcourse.services.PeopleService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BooksService booksService;
    private final PeopleService peopleService;

    @Autowired
    public BooksController(BooksService booksService, PeopleService peopleService) {
        this.booksService = booksService;
        this.peopleService = peopleService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("books", booksService.findAll());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model){
        Book book = booksService.findOne(id);
        System.out.println(book);
        model.addAttribute("book", book);
        model.addAttribute("personAssigned", book.getAssignedto());
        model.addAttribute("person", new Person());
        model.addAttribute("people", peopleService.findAll());
        return "books/show";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping("")
    public String newBook(@ModelAttribute ("book") Book book){
        booksService.save(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable ("id") int id, Model model){
        model.addAttribute("book", booksService.findOne(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String saveChanges(@PathVariable("id") int id, @ModelAttribute ("book") Book book){
        booksService.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        booksService.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("pers") Person person){
        booksService.assign(id, person);
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        booksService.release(id);
        return "redirect:/books/" + id;
    }

}
