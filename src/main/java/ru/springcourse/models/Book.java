package ru.springcourse.models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="title")
    private String title;
    @Column(name="author")
    private String author;
    @Column(name="year")
    private int year;
    @ManyToOne
    @JoinColumn(name="assignedto", referencedColumnName = "id")
    private Person assignedto;
    @Column(name="taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date takenAt;
    @Transient
    private boolean overdue;

    public Book() {
    }

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(Person assignedto) {
        this.assignedto = assignedto;
        if (assignedto == null){
            this.setTakenAt(null);
            this.setOverdue(false);
        } else {
            this.setTakenAt(new Date());
            this.setOverdue(false);
        }
    }

    public Date getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(Date takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isOverdue() {
        if (getTakenAt() == null) {
            setOverdue(false);
            return overdue;
        }
        LocalDate dateTaken = new java.sql.Date(getTakenAt().getTime()).toLocalDate();
        LocalDate dateOverdue = dateTaken.plusDays(10);
        overdue = dateOverdue.isBefore(LocalDate.now());
        System.out.println(dateTaken + " isOverdue? " + overdue);
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", assignedto=" + assignedto +
                '}';
    }
}
