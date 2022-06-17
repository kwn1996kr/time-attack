package com.example.java0617.service;

import com.example.java0617.model.Person;
import com.example.java0617.model.PersonRepository;
import com.example.java0617.model.PersonRequestDto;
import com.sun.tools.javac.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Transactional
    public Long update(Long id, PersonRequestDto personRequestDto){
        Person person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );
        person.update(personRequestDto);
        return person.getIdx();
    }


    public List<Person> recommend(Long id, PersonRequestDto personRequestDto){
        Person person = personRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 아이디가 존재하지 않습니다.")
        );

        return personRepository.findByAgeAndGender(person.getAge(),person.getGender());
    }
}
