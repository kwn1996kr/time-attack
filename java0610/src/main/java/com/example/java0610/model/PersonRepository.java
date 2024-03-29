package com.example.java0610.model;

import com.sun.tools.javac.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person,Long> {
    List<Person> findByAgeAndGender(int age, int gender);
}

