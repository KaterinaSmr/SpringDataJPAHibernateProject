package ru.springcourse.models;

public class Book {
    private int id;
    private String title;
    private String author;
    private int year;
    private int assignedto;

    public Book() {
    }

    public Book(int id, String title, String author, int year, int assignedto) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.assignedto = assignedto;
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

    public int getAssignedto() {
        return assignedto;
    }

    public void setAssignedto(int assignedto) {
        this.assignedto = assignedto;
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
