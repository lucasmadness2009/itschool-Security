package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Subject;

public interface SubjectRepository extends JpaRepository<Subject, Integer> {
}
