package ru.gb.spring.homework.sem2;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository repository;

    /**
     * 3.1 GET /student/{id} - получить студента по ID
     */
    @GetMapping("{id}")
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        Student student = repository.getById(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    /**
     * 3.3 GET /student/search?name='studentName' - получить список студентов, чье имя содержит подстроку studentName
     */
    @GetMapping("/search")
    public List<Student> getStudentsByName(@RequestParam("name") String name) {
        return repository.getByName(name);
    }

    /**
     * 3.2 GET /student - получить всех студентов
     */
    @GetMapping
    public List<Student> getAllStudents() {
        return repository.getAll();
    }

    /**
     * 3.5 POST /student - создать студента (принимает JSON) (отладиться можно с помощью Postman)
     */
    @PostMapping(value = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
        if (student.getId() == null || student.getId() <= 0) {
            return new ResponseEntity<>(student, HttpStatus.NOT_ACCEPTABLE);
        }

        repository.add(student);
        return ResponseEntity.ok(student);
    }

    /**
     * 3.6 DELETE /student/{id} - удалить студента
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Student> deleteStudent(@PathVariable("id") Long id) {
        Student student = repository.delete(id);
        if (student != null) {
            return ResponseEntity.ok(student);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @PutMapping(value = "/update/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> updateStudent(@PathVariable("id") Long id, @RequestBody Student student) {

        if (id == null || id <= 0L) {
            return new ResponseEntity<>(student, HttpStatus.NOT_ACCEPTABLE);
        }

        return ResponseEntity.ok(repository.update(id, student));
    }

}
