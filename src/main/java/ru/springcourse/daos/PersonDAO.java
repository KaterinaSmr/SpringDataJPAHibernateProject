package ru.springcourse.daos;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.springcourse.models.Person;
import java.util.List;

@Component
public class PersonDAO {
//    private final JdbcTemplate jdbcTemplate;
//
//    public PersonDAO(JdbcTemplate jdbcTemplate) {
//        this.jdbcTemplate = jdbcTemplate;
//    }
//
//    public List<Person> index(){
//        return jdbcTemplate.query("select * from person", new BeanPropertyRowMapper<>(Person.class));
//    }
//
//    public Person show(int id){
//        return jdbcTemplate.query("select * from person where id = ?", new Object[]{id},
//                new BeanPropertyRowMapper<>(Person.class)).get(0);
//    }
//
//    public void add(Person person) {
//        jdbcTemplate.update("insert into person (name, year) values (?,?)",
//                person.getName(), person.getYear());
//    }
//
//    public void update(int id, Person person) {
//        jdbcTemplate.update("update person set name=?, year=? where id=?",
//                person.getName(), person.getYear(), id);
//    }
//
//    public void delete(int id) {
//        jdbcTemplate.update("delete from person where id=?", id);
//    }
}
