package ro.itschool.runatstartup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import ro.itschool.entity.*;
import ro.itschool.service.*;

import java.util.List;

@Component
public class RunAtStartup {

  @Autowired
  private StudentService studentService;

  @Autowired
  private TeacherService teacherService;

  @Autowired
  private SubjectService subjectService;

  @Autowired
  private AdminService adminService;

  @Autowired
  private StudentSubjectGradeService studentSubjectGradeService;

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

    Admin admin = new Admin();
    admin.setUsername("admin");
    admin.setRole(ITSchoolRole.TEACHER);
    admin.setMail("admin@admin.com");
    admin.setPassword("admin");
    adminService.registerAdmin(admin);

    var subjectBiologie = new Subject(SubjectEnum.BIOLOGIE);
    subjectService.saveSubject(subjectBiologie);
    var subjectFizica = new Subject(SubjectEnum.FIZICA);
    subjectService.saveSubject(subjectFizica);
    var subjectInformatica = new Subject(SubjectEnum.INFORMATICA);
    subjectService.saveSubject(subjectInformatica);
    var subjectChimie = new Subject(SubjectEnum.CHIMIE);
    subjectService.saveSubject(subjectChimie);
    var subjectMate = new Subject(SubjectEnum.MATE);
    subjectService.saveSubject(subjectMate);
    var subjectEngleza = new Subject(SubjectEnum.ENGLEZA);
    subjectService.saveSubject(subjectEngleza);
    var subjectSport = new Subject(SubjectEnum.SPORT);
    subjectService.saveSubject(subjectSport);

    StudentSubjectGrade studentSubjectGrade1 = new StudentSubjectGrade();
    studentSubjectGrade1.setGrade(List.of(10, 10, 10, 7, 6, 9, 8));
    studentSubjectGrade1.setSubject(subjectBiologie);
    studentSubjectGrade1.setStudent(student1);

    studentSubjectGradeService.saveStudentSubjectGrade(
            new StudentSubjectGrade(student1, subjectBiologie, List.of(7, 8, 9, 10, 10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGrade(
            new StudentSubjectGrade(student1, subjectFizica, List.of(10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGrade(
            new StudentSubjectGrade(student2, subjectMate, List.of(9, 10, 10, 9, 8)));
    studentSubjectGradeService.saveStudentSubjectGrade(
            new StudentSubjectGrade(student2, subjectSport, List.of(10, 10)));

  }
}
