package ru.gb.spring.homework.sem2;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//@AllArgsConstructor(onConstructor_ = {@JsonCreator})
public class Student {

    private static Long idCounter = 1L;
    private final Long id;
    private String name;
    private Group groupName;

    public Student(String name, Group groupName) {
        this.id = idCounter++;
        this.name = name;
        this.groupName = groupName;
    }
}
