package ro.itschool.runatstartup;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.*;
import ro.itschool.service.*;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RunAtStartup {

  private final StudentService studentService;
  private final TeacherService teacherService;
  private final SubjectService subjectService;
  private final AdminService adminService;
  private final StudentSubjectGradeService studentSubjectGradeService;

  @EventListener(ApplicationReadyEvent.class)
  public void insertDataIntoDBAfterServerStartup() {
    Student student1 = new Student();
    student1.setGrade(Grade.IX);
    student1.setUsername("student1");
    student1.setRole(ITSchoolRole.STUDENT);
    student1.setMail("student1@student.com");
    student1.setPassword("student1");

    Student student2 = new Student();
    student2.setGrade(Grade.X);
    student2.setUsername("student2");
    student2.setRole(ITSchoolRole.STUDENT);
    student2.setMail("student2@student.com");
    student2.setPassword("student2");

    studentService.registerStudent(student1);
    studentService.registerStudent(student2);

    Teacher teacher = new Teacher();
    teacher.setUsername("teacher");
    teacher.setRole(ITSchoolRole.TEACHER);
    teacher.setMail("teacher@teacher.com");
    teacher.setPassword("teacher");
    teacher.setSubjects(List.of(SubjectEnum.MATE, SubjectEnum.CHIMIE, SubjectEnum.FIZICA));
    teacherService.registerTeacher(teacher);

    Teacher teacher2 = new Teacher();
    teacher2.setUsername("teacher2");
    teacher2.setRole(ITSchoolRole.TEACHER);
    teacher2.setMail("teacher2@teacher.com");
    teacher2.setPassword("teacher2");
    teacher2.setSubjects(List.of(SubjectEnum.ROMANA, SubjectEnum.SPORT, SubjectEnum.ENGLEZA));
    teacherService.registerTeacher(teacher2);

    Admin admin = new Admin();
    admin.setUsername("admin");
    admin.setRole(ITSchoolRole.TEACHER);
    admin.setMail("admin@admin.com");
    admin.setPassword("admin");
    admin.setHiringDate(LocalDateTime.of(2012, Month.DECEMBER, 15, 0, 0));
    adminService.registerAdmin(admin);

    var subjectList = Arrays.stream(SubjectEnum.values())
            .map(Subject::new)
            .toList();
    subjectService.saveAllAvailableSubjectAtStartup(subjectList);

    StudentSubjectGrade studentSubjectGrade1 = new StudentSubjectGrade();
    studentSubjectGrade1.setGrades(List.of(10, 9, 8));
    studentSubjectGrade1.setSubject(subjectList.get(0));
    studentSubjectGrade1.setStudent(student1);

    studentSubjectGradeService.saveStudentSubjectGradeAtStartup(
            new StudentSubjectGrade(student1, subjectList.get(2), teacher, List.of(7, 8, 9, 10, 10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGradeAtStartup(
            new StudentSubjectGrade(student1, subjectList.get(3), teacher2, List.of(10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGradeAtStartup(
            new StudentSubjectGrade(student2, subjectList.get(5), teacher, List.of(9, 10, 10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGradeAtStartup(
            new StudentSubjectGrade(student2, subjectList.get(2), teacher2, List.of(10, 10)));

  }
}
