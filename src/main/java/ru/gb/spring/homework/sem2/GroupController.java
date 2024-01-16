package ru.gb.spring.homework.sem2;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository repository;

    /**
     * 3.4 GET /group/{groupName}/student - получить всех студентов группы
     */
    @GetMapping("{groupName}/student")
    public List<Student> getStudentsByGroup(@PathVariable("groupName") String group) {
        return repository.getByGroup(group);
    }
}
