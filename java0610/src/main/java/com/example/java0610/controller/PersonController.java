package com.example.java0610.controller;

import com.example.java0610.model.Person;
import com.example.java0610.model.PersonRepository;
import com.example.java0610.model.PersonRequestDto;
import com.example.java0610.service.PersonService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
public class PersonController {

    private final PersonRepository personRepository;
    private final PersonService personService;

    @PostMapping("/create")
    public Person createUser(@RequestBody PersonRequestDto usersRequestDto){

        Person person = new Person(usersRequestDto);

        return personRepository.save(person);
    }

    @GetMapping("/api/person")
    public List<Person> getPersons() {
        return personRepository.findAll();
    }

    @GetMapping("/read/{idx}")
    public Person getperson(@PathVariable Long idx) {
        Person person = personRepository.findById(idx).orElse(null);
        return person;
    }

    @PutMapping("/update/{idx}")
    public Long updateCourse(@PathVariable Long idx, @RequestBody PersonRequestDto requestDto) {
        return personService.update(idx, requestDto);
    }

    @DeleteMapping("/delete/{idx}")
    public Long deleteCourse(@PathVariable Long idx) {
        personRepository.deleteById(idx);
        return idx;
    }

    //유저 추천
    @GetMapping("/recommend/{idx}")
    public List<Person> getPersons(@PathVariable Long idx) {
        return personRepository.findAll();
    }
}
