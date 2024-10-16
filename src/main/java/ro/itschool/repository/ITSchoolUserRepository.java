package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.ITSchoolUser;

import java.util.Optional;

public interface ITSchoolUserRepository extends JpaRepository<ITSchoolUser, Integer> {
    Optional<ITSchoolUser> findByUsername(String username);
    //aduce tot itschooluserul, care poate fi au poate sa nu fie existent
}
