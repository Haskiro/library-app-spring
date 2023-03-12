package com.github.haskiro.models;

import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;

public class Book {
    private int bookId;
    private Integer personId;
    @NotEmpty(message = "Название должно быть не пустое")
    @Size(min = 3, max = 30, message = "Название должно быть в диапазоне от 3 до 30 символов")
    private String name;

    @NotEmpty(message = "Название должно быть не пустое")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "ФИО автора должно быть в формате Имя Фамилия Отчество")
    private String author;

    @NotNull(message = "Год должен быть не пустым")
    private int year;

    public Book() {}
    public Book(int bookId, int personId, String name, String author, int year) {

        this.bookId = bookId;
        this.personId = personId;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
