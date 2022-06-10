package com.example.java0610.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class PersonRequestDto {

    private String name;
    private int age;
    private int gender;
    private String email;

    public PersonRequestDto(String name, int age, int gender, String email) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.email = email;
    }
}
