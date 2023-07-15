-- liquibase formatted sql

-- changeset lili:1
-- precondition-sql-check expectedResult:0 select count(*) from pg_catalog.pg_tables t inner join pg_indexes i on i.tablename = t.tablename where  t.tablename = 'student' and i.indexname = 'IDX_student_name';
-- rollback DROP INDEX IDX_student_name
CREATE INDEX IDX_student_name ON student (name);

-- changeset lili:2
-- precondition-sql-check expectedResult:0 select count(*) from pg_catalog.pg_tables t inner join pg_indexes i on i.tablename = t.tablename where  t.tablename = 'faculty' and i.indexname = 'IDX_faculty_color_name';
-- rollback DROP IDX_faculty_color_name
CREATE INDEX IDX_faculty_color_name ON faculty (name, color);