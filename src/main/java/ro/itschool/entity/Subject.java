package ro.itschool.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    private SubjectEnum name;

    @OneToMany(mappedBy = "subject", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<StudentSubjectGrade> studentSubjectGrades = new ArrayList<>();

    public Subject(final SubjectEnum name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public SubjectEnum getName() {
        return name;
    }

    public void setName(final SubjectEnum name) {
        this.name = name;
    }

    public List<StudentSubjectGrade> getStudentSubjectGrades() {
        return studentSubjectGrades;
    }

    public void setStudentSubjectGrades(final List<StudentSubjectGrade> studentSubjectGrades) {
        this.studentSubjectGrades = studentSubjectGrades;
    }
}
