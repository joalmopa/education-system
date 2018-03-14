package co.com.education.database.jpa.repository;

import co.com.education.database.jpa.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}
