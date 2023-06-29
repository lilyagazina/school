package ru.hogwarts.school.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class FacultyServiceTest {
    @Mock
    private FacultyRepository facultyRepository;
    @InjectMocks
    FacultyService out;

    @Test
    void createFaculty() {
        Faculty f = new Faculty(1L, "Gryffindor", "red");
        when(facultyRepository.save(f)).thenReturn(f);
        assertEquals(f, out.createFaculty(f));
    }

    @Test
    void getFacultyById() {
        Faculty f = new Faculty(1L, "Gryffindor", "red");
        when(facultyRepository.findById(1L)).thenReturn(Optional.of(f));
        assertEquals(f, out.getFacultyById(1L));
    }

    @Test
    void getStudentNegativeTest() {
        when(facultyRepository.findById(4L)).thenReturn(Optional.empty());
        assertThrows(NoSuchElementException.class, () -> out.getFacultyById(4L));
    }

    @Test
    void updateFaculty() {
        Faculty f = new Faculty(1L, "Gryffindor", "red");
        when(facultyRepository.save(f)).thenReturn(f);
        assertEquals(f, out.createFaculty(f));
    }

    @Test
    void deleteFaculty() {
    }

    @Test
    void findByColor() {
        Faculty f = new Faculty(1L, "Gryffindor", "red");
        when(facultyRepository.findByColorLikeIgnoreCase("red")).thenReturn(List.of(f));
        assertEquals(List.of(f), out.findByColorLike("red"));
    }

    @Test
    void getAllFaculty() {
    }
}