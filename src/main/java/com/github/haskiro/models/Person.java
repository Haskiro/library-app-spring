package com.github.haskiro.models;

import jakarta.validation.constraints.*;

public class Person {
    private int personId;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(max = 100, message = "Максимальная длина ФИО 100 символов")
    @Pattern(regexp = "[А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+ [А-ЯЁ][а-яё]+", message = "ФИО должно быть в формате Имя Фамилия Отчество")
    private String fullName;
    @Min(value = 1900, message = "Год рождения должен быть больше 1900")
    private int yearOfBirth;

    public Person() {}

    public Person(int personId, String fullName, int yearOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int id) {
        this.personId = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
