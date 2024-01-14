package ru.gb.spring.homework.sem2;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public enum Group {

    DESIGN ("design"),
    DEVELOPER ("developer");

    private final String title;

}
