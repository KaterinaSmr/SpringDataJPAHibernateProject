package ru.springcourse.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.models.Person;
import ru.springcourse.services.BooksService;
import ru.springcourse.services.PeopleService;


@Controller
@RequestMapping("/people")
public class PeopleController {
    private final PeopleService peopleService;
    private final BooksService booksService;

    public PeopleController(PeopleService peopleService, BooksService booksService) {
        this.peopleService = peopleService;
        this.booksService = booksService;
    }

    @GetMapping("")
    public String index(Model model){
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }
    @GetMapping("/{id}")
    public String show(@PathVariable ("id") int id, Model model){
        Person person = peopleService.findOne(id);
        model.addAttribute("person", person);
        model.addAttribute("books", person.getBooks());
        return "people/show";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("person", new Person());
        return "people/new";
    }
    @PostMapping("")
    public String newPerson(@ModelAttribute ("person") Person person){
        peopleService.save(person);
        return "redirect:/people";
    }
    @GetMapping("/{id}/edit")
    public String edit(@PathVariable ("id") int id, Model model){
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }
    @PatchMapping("/{id}")
    public String saveChanges(@PathVariable("id") int id, @ModelAttribute ("person") Person person){
        peopleService.update(id, person);
        return "redirect:/people";
    }
    @DeleteMapping("/{id}")
    public String delete(@PathVariable ("id") int id){
        peopleService.delete(id);
        return "redirect:/people";
    }

}
