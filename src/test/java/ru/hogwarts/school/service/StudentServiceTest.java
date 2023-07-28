package ru.hogwarts.school.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    private StudentRepository studentRepository;

    @InjectMocks
    StudentService out;
    ;

    @Test
    void createStudent() {
        Student s = new Student(1L, "Lili", 24,null);
        when(studentRepository.save(s)).thenReturn(s);
        assertEquals(s, out.createStudent(s));

    }

    @Test
    void getStudentById() {
        Student s = new Student(1L, "Lili", 24,null);
        when(studentRepository.findById(1L)).thenReturn(Optional.of(s));
        assertEquals(s, out.getStudentById(1L));
    }

    @Test
    void getStudentNegativeTest() {
        when(studentRepository.findById(4L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> out.getStudentById(4L));
    }

    @Test
    void updateStudent() {
        Student s = new Student(1L, "Dima", 14, null);
        when(studentRepository.save(s)).thenReturn(s);
        assertEquals(s, out.updateStudent(1L, s));
    }

    @Test
    void findByAge() {
        Student s = new Student(1L, "Lili", 24,null);
        when(studentRepository.findByAge(24)).thenReturn(List.of(s));
        assertEquals(List.of(s), out.findByAge(24));
    }
}