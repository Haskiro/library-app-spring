package com.github.haskiro.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.OffsetDateTime;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Название должно быть не пустое")
    @Size(min = 3, max = 30, message = "Название должно быть в диапазоне от 3 до 30 символов")
    @Column(name = "title")
    private String title;

    @NotEmpty(message = "Название должно быть не пустое")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "ФИО автора должно быть в формате Имя Фамилия Отчество")
    @Column(name = "author")
    private String author;

    @NotNull(message = "Год должен быть не пустым")
    @Column(name = "year")
    private int year;

    @Column(name = "taken_at")
    @Temporal(TemporalType.TIMESTAMP)
    private OffsetDateTime takenAt;

    @Transient
    private boolean toBeReturned = false;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Book() {}
    public Book(String title, String author, int year) {
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

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public OffsetDateTime getTakenAt() {
        return takenAt;
    }

    public void setTakenAt(OffsetDateTime takenAt) {
        this.takenAt = takenAt;
    }

    public boolean isToBeReturned() {
        return toBeReturned;
    }

    public void setToBeReturned(boolean toBeReturned) {
        this.toBeReturned = toBeReturned;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                '}';
    }
}
