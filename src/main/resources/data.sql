use itschool;

insert into student (mail, username, password, grade, role) values ('student1@student.ro', 'student1', '$2a$12$8PD11Ga5W55bPzw4J5zFM.r4nA1qJmX3kMR0bZvdKG4O6vws7JaFq', 'X', 'STUDENT');
insert into student (mail, username, password, grade, role) values ('student2@student.ro', 'student2', '$2a$12$MdMwKu2BHJEUw66phhW/kumywUPjXXBSEi2ZWkDwWH9A20BJ7yv9.', 'XI', 'STUDENT');

insert into subject (name) values ('ROMANA');
insert into subject (name) values ('MATE');
insert into subject (name) values ('FIZICA');
insert into subject (name) values ('CHIMIE');
insert into subject (name) values ('BIOLOGIE');
insert into subject (name) values ('INFORMATICA');
insert into subject (name) values ('SPORT');
insert into subject (name) values ('FRANCEZA');
insert into subject (name) values ('ENGLEZA');

insert into student_subject_grade (student_id, subject_id, grade) values (1, 1, 9);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 1, 8);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 1, 10);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 2, 10);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 2, 9);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 2, 7);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 3, 10);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 3, 6);
insert into student_subject_grade (student_id, subject_id, grade) values (1, 3, 7);

insert into student_subject_grade (student_id, subject_id, grade) values (2, 3, 7);
insert into student_subject_grade (student_id, subject_id, grade) values (2, 3, 9);
insert into student_subject_grade (student_id, subject_id, grade) values (2, 4, 9);
insert into student_subject_grade (student_id, subject_id, grade) values (2, 4, 10);
insert into student_subject_grade (student_id, subject_id, grade) values (2, 4, 10);