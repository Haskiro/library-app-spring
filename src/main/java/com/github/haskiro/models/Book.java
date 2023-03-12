package com.github.haskiro.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.springframework.lang.Nullable;

public class Book {
    private int bookId;
    private Integer personId;
    @NotEmpty(message = "Название должно быть не пустое")
    @Size(min = 3, max = 30, message = "Название должно быть в диапазоне от 3 до 30 символов")
    private String name;

    @NotEmpty(message = "Название должно быть не пустое")
    @Pattern(regexp = "([A-Z]\\w+ ){3}", message = "ФИО автора должно быть в формате Имя Фамилия Отчество")
    private String author;

    @NotEmpty(message = "Год должен быть не пустым")
    @Pattern(regexp = "\\d{4}", message = "Год должен быть в формате гггг (4 символа)")
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