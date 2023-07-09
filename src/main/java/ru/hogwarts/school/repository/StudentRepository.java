package ru.hogwarts.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.hogwarts.school.model.Student;

import java.util.Collection;
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    Collection<Student> findByAge(int age);
    Collection<Student> findByAgeBetween( int min, int max);
    Collection<Student> getStudentsByIdOrderByFaculty(Long facultyId);

    //Возможность получить количество всех студентов в школе. Эндпоинт должен вернуть число.
    @Query(value = "SELECT COUNT(*)  FROM student",nativeQuery = true)
    Long getTotalNumberStudents();

    //Возможность получить средний возраст студентов. Эндпоинт должен вернуть число.
    @Query(value = "SELECT avg(age) FROM student",nativeQuery = true)
    Long getAverageAgeStudents();

    //Возможность получать только пять последних студентов. Последние студенты считаются теми, у кого идентификатор больше других.
    @Query(value = "SELECT * FROM student order by id desc limit 5",nativeQuery = true)
    Collection<Student> getLastFiveStudents();


}
