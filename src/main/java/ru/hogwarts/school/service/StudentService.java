package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

   Logger logger = LoggerFactory.getLogger(StudentService.class);


    public Student createStudent(Student student) {
        logger.info("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student getStudentById(Long studentId) {
        logger.info("Was invoked method get with studentId = {}", studentId);
        return studentRepository.findById(studentId).get();
    }

    public Student updateStudent(Long studentId, Student student) {
        logger.info("Was invoked method update with studentId = {}", studentId);
        return studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        logger.info("Was invoked method delete with studentId = {}", studentId);
        studentRepository.deleteById(studentId);
    }

    public Collection<Student> getAllStudent() {
        logger.info("Was invoked method getAllStudent ");
        return studentRepository.findAll();
    }

    public Collection<Student> findByAge(int age) {
        logger.info("Was invoked method findByAge");
        return studentRepository.findByAge(age);
    }

    public Collection<Student> findByAgeBetween(int min, int max) {
        logger.info("Was invoked method findByAgeBetween");
        return studentRepository.findByAgeBetween(min, max);
    }

    public Faculty getFacultyStudent(Long id) {
        logger.info("Was invoked method getFacultyStudent with id = {}", id);
        Student student = studentRepository.findById(id).orElse(null);
        if (student == null) {
            return null;
        }
        return student.getFaculty();

    }
    public Long getTotalNumberStudents(){
        logger.info("Was invoked method getTotalNumberStudents");
       return studentRepository.getTotalNumberStudents();

    }

    public Long getAverageAgeStudents(){
        logger.info("Was invoked method getAverageAgeStudents");
       return studentRepository.getAverageAgeStudents();
    }
    public Collection<Student> getLastFiveStudents(){
        logger.info("Was invoked method getLastFiveStudents");
        return studentRepository.getLastFiveStudents();
    }

}
