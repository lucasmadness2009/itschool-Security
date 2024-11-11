package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class StudentSubjectGrade {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "student_id", referencedColumnName = "id")
  private Student student;

  @ManyToOne
  @JoinColumn(name = "subject_id", referencedColumnName = "id")
  private Subject subject;

  @ManyToOne
  @JoinColumn(name = "teacher_id", referencedColumnName = "id")
  private Teacher teacher;

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Integer> grades;

  @Override
  public String toString() {
    return "StudentSubjectGrade: Student: " + student.getUsername()
            + " Subject: " + subject.getSubjectEnum()
            + " Grade: " + grades;

  }

  public StudentSubjectGrade() {
  }

  public StudentSubjectGrade(
          final Student student,
          final Subject subject,
          final Teacher teacher,
          final List<Integer> grades) {
    this.student = student;
    this.subject = subject;
    this.grades = grades;
    this.teacher = teacher;
  }

}
