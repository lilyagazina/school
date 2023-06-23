package ru.hogwarts.school.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.FacultyService;

import java.util.Collection;
import java.util.Collections;


@RestController
@RequestMapping("faculty")
public class FacultyController {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @PostMapping
    public Faculty createFaculty(@RequestBody Faculty faculty) {
        Faculty createdFaculty = facultyService.createFaculty(faculty);
        return ResponseEntity.ok(createdFaculty).getBody();
    }


    @GetMapping("{facultyId}")
    public ResponseEntity<Faculty> getFacultyById(@PathVariable Long facultyId) {
        Faculty createdFaculty = facultyService.getFacultyById(facultyId);
        if (createdFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(createdFaculty);
    }

    @PutMapping
    public ResponseEntity<Faculty> updateFaculty(@RequestBody Long facultyId, Faculty faculty) {
        Faculty createdFaculty = facultyService.updateFaculty(facultyId, faculty);
        if (createdFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(createdFaculty);
    }

    @DeleteMapping("{facultyId}")
    public ResponseEntity deleteFaculty(@PathVariable Long facultyId) {
         facultyService.deleteFaculty(facultyId);
         return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<Collection<Faculty>> findFaculties(@RequestParam(required = false) String color) {
        if (color != null && !color.isBlank()) {
            return ResponseEntity.ok(facultyService.findByColorLike(color));
        }
        return ResponseEntity.ok(Collections.emptyList());
    }

    @GetMapping("getAll")
    public ResponseEntity<Collection<Faculty>> getAllFaculty() {
        return ResponseEntity.ok(facultyService.getAllFaculty());
    }
}
