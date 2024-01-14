package ru.gb.spring.homework.sem2;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

@Component
public class StudentRepository {

    private List<Student> students;

    @PostConstruct
    private void create() {
        List<Group> groups = List.of(Group.values());

        students = LongStream.rangeClosed(1, 10)
                .mapToObj(it -> new Student("Student #" + it, groups
                        .get(ThreadLocalRandom.current().nextInt(0, 2))))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Student> getByGroup(String group) {
        return students.stream()
                .filter(it -> Objects.equals(it.getGroupName().getTitle(), group))
                .toList();

    }

    /**
     * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
     */
    public List<Student> getByName(String name) {
        return students.stream()
                .filter(it -> it.getName().contains(name))
                .toList();
    }

    /**
     * 3.1 GET /student/{id} - получить студента по ID
     */
    public Student getById(Long id) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElse(null);
    }

    /**
     * 3.2 GET /student - получить всех студентов
     */
    public List<Student> getAll() {
        return List.copyOf(students);
    }

    public void add(Student student) {
        students.add(student);
    }

    public Student delete(Long id) {
        Student student = getById(id);

        if (student != null) {
            students.remove(student);
        }
        return student;
    }

    public Student update(Long id, Student student) {
        return students.stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .peek(it -> it.setName(student.getName()))
                .peek(it -> it.setGroupName(student.getGroupName()))
                .findFirst()
                .orElse(null);
    }
}
