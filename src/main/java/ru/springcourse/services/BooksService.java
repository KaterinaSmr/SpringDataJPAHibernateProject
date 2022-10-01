package ru.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;
import ru.springcourse.repositories.BooksRepository;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }
    public List<Book> findAll(boolean sort){
        if (sort){
            return booksRepository.findAll(Sort.by("year"));
        } else {
            return booksRepository.findAll();
        }
    }
    public List<Book> findAll(int page, int itemsPerPage, boolean sort){
        if (sort) {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by("year"))).getContent();
        } else {
            return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
        }
    }
    public Book findOne(int id){
        Optional<Book> optionalBook = booksRepository.findById(id);
        return optionalBook.orElse(null);
    }
    public List<Book> findByTitleContaining(String text){
        return booksRepository.findByTitleContaining(text);
    }
    @Transactional
    public void save(Book book){
        booksRepository.save(book);
    }
    @Transactional
    public void update(int id, Book updatedBook){
        updatedBook.setId(id);
        booksRepository.save(updatedBook);
    }
    @Transactional
    public void delete(int id){
        booksRepository.deleteById(id);
    }
    @Transactional
    public void assign(int bookId, Person person){
        Book book = findOne(bookId);
        book.setAssignedto(person);
        booksRepository.save(book);
    }
    @Transactional
    public void release(int bookId){
        Book book = findOne(bookId);
        book.setAssignedto(null);
        booksRepository.save(book);
    }
}
