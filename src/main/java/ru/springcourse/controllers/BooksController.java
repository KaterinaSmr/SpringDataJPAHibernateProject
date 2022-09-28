package ru.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.daos.BookDAO;
import ru.springcourse.daos.PersonDAO;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;

@Controller
@RequestMapping("/books")
public class BooksController {
    private final BookDAO bookDAO;
    private final PersonDAO personDAO;

    @Autowired
    public BooksController(BookDAO bookDAO, PersonDAO personDAO) {
        this.bookDAO = bookDAO;
        this.personDAO = personDAO;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("books", bookDAO.index());
        return "books/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        model.addAttribute("person", bookDAO.getAssignedToPerson(id));
        model.addAttribute("people", personDAO.index());
        return "books/show";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("book", new Book());
        return "books/new";
    }
    @PostMapping("")
    public String newBook(@ModelAttribute ("book") Book book){
        bookDAO.add(book);
        return "redirect:/books";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable ("id") int id, Model model){
        model.addAttribute("book", bookDAO.show(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String saveChanges(@PathVariable("id") int id, @ModelAttribute ("book") Book book){
        bookDAO.update(id, book);
        return "redirect:/books";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        bookDAO.delete(id);
        return "redirect:/books";
    }

    @PatchMapping("/{id}/assign")
    public String assign(@PathVariable("id") int id, @ModelAttribute("person") Person person){
        bookDAO.assign(id, person);
        return "redirect:/books/" + id;
    }
    @PatchMapping("/{id}/release")
    public String release(@PathVariable("id") int id){
        bookDAO.release(id);
        return "redirect:/books/" + id;
    }

}
