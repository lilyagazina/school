/*первый JOIN-запрос, информация обо
  всех студентах школы Хогвартс вместе с названиями факультетов.*/
SELECT student.name, student.age, faculty.name
FROM student
         INNER JOIN faculty on student.faculty_id = faculty.id;

/*второй JOIN-запрос, студенты у которых есть аватарки.*/
SELECT student.name, avatar.student_id
FROM student
         INNER JOIN avatar on student.id = avatar.student_id