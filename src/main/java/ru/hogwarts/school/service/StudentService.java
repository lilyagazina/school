package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class StudentService {
    private Map<Long, Student> students = new HashMap<>();
    private long generatedId = 0;

    public Student createStudent(Student student) {
        student.setId(++generatedId);
        students.put(generatedId, student);
        return student;
    }

    public Student getStudentById(Long studentId) {
        return students.get(studentId);
    }

    public Student updateStudent(Long studentId, Student student) {
        students.put(generatedId, student);
        return student;
    }

    public Student deleteStudent(Long studentId) {
        return students.remove(studentId);
    }

    public Collection<Student> getAllStudent() {
        return students.values();
    }

    public Collection<Student> findByAge(int age) {
        ArrayList<Student> result = new ArrayList<>();
        for (Student student : students.values()) {
            if (student.getAge() == age) {
                result.add(student);
            }
        }
        return result;
    }
}
