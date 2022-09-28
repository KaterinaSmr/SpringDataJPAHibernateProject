package ru.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.daos.BookDAO;
import ru.springcourse.daos.PersonDAO;
import ru.springcourse.models.Person;

@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PersonDAO personDAO;
    private final BookDAO bookDAO;

    public PeopleController(PersonDAO personDAO, BookDAO bookDAO) {
        this.personDAO = personDAO;
        this.bookDAO = bookDAO;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("people", personDAO.index());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        model.addAttribute("books", bookDAO.booksForPerson(id));
        return "people/show";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping("")
    public String newPerson(@ModelAttribute ("person") Person person){
        personDAO.add(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable ("id") int id, Model model){
        model.addAttribute("person", personDAO.show(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String saveChanges(@PathVariable("id") int id, @ModelAttribute ("person") Person person){
        personDAO.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        personDAO.delete(id);
        return "redirect:/people";
    }

}
