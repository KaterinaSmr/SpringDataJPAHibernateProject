package ru.springcourse.daos;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.springcourse.models.Book;
import ru.springcourse.models.Person;

import java.util.List;

@Component
public class BookDAO {
//    private final JdbcTemplate jdbcTemplate;
//    private final PersonDAO personDAO;
//
//    public BookDAO(JdbcTemplate jdbcTemplate, PersonDAO personDAO) {
//        this.jdbcTemplate = jdbcTemplate;
//        this.personDAO = personDAO;
//    }
//
//    public List<Book> index(){
//        return jdbcTemplate.query("SELECT * FROM book", new BookRowMapper());
//    }
//
//    public Book show(int id){
//        return jdbcTemplate.query("select * from book where id = ?", new Object[]{id},
//                new BookRowMapper()).get(0);
////                new BeanPropertyRowMapper<>(Book.class)).get(0);
//    }
//
//    public void add(Book book) {
//        jdbcTemplate.update("insert into book (title, author, year) values (?,?,?)",
//                book.getTitle(), book.getAuthor(), book.getYear());
//    }
//
//    public void update(int id, Book book) {
//        jdbcTemplate.update("update book set title=?, author=?, year=? where id=?",
//                book.getTitle(), book.getAuthor(), book.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from book where id=?", id);
//    }
//
//    public Person getAssignedToPerson(int id){
//        return jdbcTemplate.query("SELECT person.* FROM book JOIN person ON book.assignedto=person.id WHERE book.id=?",
//                new Object[]{id}, new BeanPropertyRowMapper<>(Person.class)).stream().findAny().orElse(new Person());
//    }
//
//    public void assign(int id, Person person) {
//        jdbcTemplate.update("update book set assignedto=? where id=?", person.getId(), id);
//    }
//
//    public void release(int id) {
//        jdbcTemplate.update("update book set assignedto=? where id=?", null, id);
//    }
//
//    public List<Book> booksForPerson(int personId){
//        return jdbcTemplate.query("select * from book where assignedto=?", new Object[]{personId},
//                new BookRowMapper());
//    }
}
