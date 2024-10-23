package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
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

  public StudentSubjectGrade(final Student student, final Subject subject, final List<Integer> grade) {
    this.student = student;
    this.subject = subject;
    this.grade = grade;
  }
}
