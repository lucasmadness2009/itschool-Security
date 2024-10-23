package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

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

  @ElementCollection(fetch = FetchType.EAGER)
  private List<Integer> grade;

  @Override
  public String toString() {
    return "StudentSubjectGrade: Student: " + student.getUsername()
            + " Subject: " + subject.getName()
            + " Grade: " + grade;

  }

  public StudentSubjectGrade() {
  }

  public StudentSubjectGrade(final Student student, final Subject subject, final List<Integer> grade) {
    this.student = student;
    this.subject = subject;
    this.grade = grade;
  }

  public Long getId() {
    return id;
  }

  public void setId(final Long id) {
    this.id = id;
  }

  public Student getStudent() {
    return student;
  }

  public void setStudent(final Student student) {
    this.student = student;
  }

  public Subject getSubject() {
    return subject;
  }

  public void setSubject(final Subject subject) {
    this.subject = subject;
  }

  public List<Integer> getGrade() {
    return grade;
  }

  public void setGrade(final List<Integer> grade) {
    this.grade = grade;
  }
}
