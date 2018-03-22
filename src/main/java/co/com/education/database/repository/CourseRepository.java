package co.com.education.database.repository;

import co.com.education.database.entity.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {

}
