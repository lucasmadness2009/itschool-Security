package ro.itschool.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.itschool.entity.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}