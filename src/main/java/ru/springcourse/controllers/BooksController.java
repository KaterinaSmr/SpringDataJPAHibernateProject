package ru.springcourse.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;
import ru.springcourse.services.BooksService;
import ru.springcourse.services.PeopleService;

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
    public String index(@RequestParam(name = "page", required = false) Integer page,
                        @RequestParam(name="books_per_page", required = false) Integer booksPerPage,
                        @RequestParam(name="sort_by_year", required = false) Boolean sort, Model model){
        if (sort == null){
            sort = false;
        }
        List<Book> books;
        if (booksPerPage != null && page != null){
            books = booksService.findAll(page, booksPerPage, sort);
        } else {
           books = booksService.findAll(sort);
        }
        model.addAttribute("books", books);
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

    @GetMapping("/search")
    public String search(){
        return "books/search";
    }
    @GetMapping(value = "/search", params = "text")
    public String searchResult(@RequestParam("text") String text, Model model){
        System.out.println("Parameter text : " + text);
        List<Book> books = booksService.findByTitleContaining(text);
        System.out.println("Books: " + books);
        model.addAttribute("books", books);
        return "books/search";
    }

}
