package com.example.java0610.service;

import com.example.java0610.model.Person;
import com.example.java0610.model.PersonRepository;
import com.example.java0610.model.PersonRequestDto;
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
}
