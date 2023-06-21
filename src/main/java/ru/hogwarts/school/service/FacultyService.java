package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.*;

@Service
public class FacultyService {
    private Map<Long, Faculty> facultys = new HashMap<>();
    private Long generatedId = 1L;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++generatedId);
        facultys.put(generatedId, faculty);
        return faculty;
    }

    public Faculty getFacultyById(Long facultyId) {
        return facultys.get(facultyId);
    }

    public Faculty updateFaculty(Long facultyId, Faculty faculty) {
        facultys.put(generatedId, faculty);
        return faculty;
    }

    public Faculty deleteFaculty(Long facultyId) {
        return facultys.remove(facultyId);
    }

    public Collection<Faculty> findByColor(String color) {
        ArrayList<Faculty> result = new ArrayList<>();
        for (Faculty faculty : facultys.values()) {
            if (Objects.equals(faculty.getColor(), color)) {
                result.add(faculty);
            }
        }
        return result;
    }

    public Collection<Faculty> getAllFaculty() {
        return facultys.values();
    }
}
