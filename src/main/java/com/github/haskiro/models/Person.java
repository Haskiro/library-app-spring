package com.github.haskiro.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Person {
    private int personId;
    @NotEmpty(message = "ФИО не должно быть пустым")
    @Size(min = 6, max = 100, message = "ФИО должно быть длинной в диапазоне от 6 до 100 символов")
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
