package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.Collection;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    //private final AvatarRepository avatarRepository;

   public StudentService(StudentRepository studentRepository/*, AvatarRepository avatarRepository*/) {
        this.studentRepository = studentRepository;
        //this.avatarRepository = avatarRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent(Long studentId, Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }

    public Collection<Student> getAllStudent() {
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFacultyStudent(Long id) {
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return student.getFaculty();

    }
    public Long getTotalNumberStudents(){
       return studentRepository.getTotalNumberStudents();

    }

    public Long getAverageAgeStudents(){
       return studentRepository.getAverageAgeStudents();
    }
    public Collection<Student> getLastFiveStudents(){
       return studentRepository.getLastFiveStudents();
    }

}
