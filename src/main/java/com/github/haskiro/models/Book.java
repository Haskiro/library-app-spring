package com.github.haskiro.models;

import jakarta.validation.constraints.*;
import org.springframework.lang.Nullable;

public class Book {
    private int id;
    @NotEmpty(message = "Название должно быть не пустое")
    @Size(min = 3, max = 30, message = "Название должно быть в диапазоне от 3 до 30 символов")
    private String title;

    @NotEmpty(message = "Название должно быть не пустое")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "ФИО автора должно быть в формате Имя Фамилия Отчество")
    private String author;

    @NotNull(message = "Год должен быть не пустым")
    private int year;

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
}
