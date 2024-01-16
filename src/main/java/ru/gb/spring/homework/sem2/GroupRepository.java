package ru.gb.spring.homework.sem2;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GroupRepository {

    private final StudentRepository studentRepository;

    /**
     * 3.4 GET /group/{groupName}/student - получить всех студентов группы
     */
    public List<Student> getByGroup(String group) {
        return studentRepository.getAll().stream()
                .filter(it -> Objects.equals(it.getGroupName().getTitle(), group))
                .toList();

    }

}
