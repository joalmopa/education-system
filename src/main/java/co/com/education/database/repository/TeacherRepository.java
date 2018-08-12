package co.com.education.database.repository;

import co.com.education.database.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TeacherRepository extends JpaRepository<TeacherEntity, Integer> {

}
