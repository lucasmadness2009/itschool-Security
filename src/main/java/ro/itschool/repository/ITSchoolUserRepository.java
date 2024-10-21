package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.ITSchoolUser;
import ro.itschool.entity.Teacher;

import java.util.Optional;

public interface ITSchoolUserRepository extends JpaRepository<ITSchoolUser, Integer> {
    Optional<Teacher> findByUsername(String username);
}
