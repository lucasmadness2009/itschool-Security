package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Admin;
import ro.itschool.entity.Teacher;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
  Optional<Admin> findByUsername(String username);

}
