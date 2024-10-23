package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Admin;

public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
