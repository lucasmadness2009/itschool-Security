package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Subject;
import ro.itschool.entity.SubjectEnum;

import java.util.Optional;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {

  Optional<Subject> findBySubjectEnum(SubjectEnum name);
}
