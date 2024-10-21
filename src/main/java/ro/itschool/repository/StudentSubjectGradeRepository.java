package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.StudentSubjectGrade;

import java.util.List;

public interface StudentSubjectGradeRepository extends JpaRepository<StudentSubjectGrade, Integer> {
    List<StudentSubjectGrade> findByStudentId(final Integer studentId);
}
