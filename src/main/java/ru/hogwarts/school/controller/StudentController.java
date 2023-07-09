package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping("student")
public class StudentController {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping
    public Student createStudent(@RequestBody Student student) {
        Student createdStudent = studentService.createStudent(student);
        return ResponseEntity.ok(createdStudent).getBody();
    }

    @PutMapping
    public ResponseEntity<Student> updateStudent(@RequestBody Long studentId, Student student) {
        Student createdStudent = studentService.updateStudent(studentId, student);
        if (createdStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdStudent);
    }

    @DeleteMapping("{studentId}")
    public ResponseEntity<Student> deleteStudent(@PathVariable Long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable Long studentId) {
        Student createdStudent = studentService.getStudentById(studentId);
        if (createdStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/age")
    public ResponseEntity<Collection<Student>> getStudentById(@PathVariable int age) {
        Collection<Student> createdStudent = studentService.findByAge(age);
        if (createdStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdStudent);
    }

    @GetMapping("/ageBetween")
    public ResponseEntity<Collection<Student>> findStudents(@RequestParam(required = false) int min,
                                                            @RequestParam(required = false) int max) {

        return ResponseEntity.ok(studentService.findByAgeBetween(min, max));

    }

    @GetMapping("/getAll")
    public ResponseEntity<Collection<Student>> getAllStudent() {
        return ResponseEntity.ok(studentService.getAllStudent());
    }

    @GetMapping("/facultys/{studentId}")
    public ResponseEntity<Faculty> getFacultyStudent(@RequestParam(required = false) Long studentId) {
        Faculty createdStudent = studentService.getFacultyStudent(studentId);
        if (createdStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdStudent);
    }
    @GetMapping("/totalNumber")
    public ResponseEntity<Long> getTotalNumberStudents(){
        return ResponseEntity.ok(studentService.getTotalNumberStudents());
    }
    @GetMapping("/averageAge")
    public ResponseEntity<Long> getAverageAgeStudents(){
        return ResponseEntity.ok(studentService.getAverageAgeStudents());
    }
    @GetMapping("/lastFiveStudents")
    public ResponseEntity<Collection<Student>> getLastFiveStudents(){
        return ResponseEntity.ok(studentService.getLastFiveStudents());
    }


}





