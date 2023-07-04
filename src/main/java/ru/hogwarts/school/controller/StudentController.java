package ru.hogwarts.school.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;


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
    /*@PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar(@PathVariable Long id, @RequestParam MultipartFile avatar) throws IOException {
        if (avatar.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }
        studentService.uploadAvatar(id, avatar);
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar(@PathVariable Long id) {
        Avatar avatar = studentService.findAvatar(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }

    @GetMapping(value = "/{id}/avatar")
    public void downloadAvatar(@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = studentService.findAvatar(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setStatus(200);
            response.setContentType(avatar.getMediaType());
            response.setContentLength((int) avatar.getFileSize());
            is.transferTo(os);
        }
    }
}*/
}





