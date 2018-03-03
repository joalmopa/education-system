package co.com.education.database.jpa.repository;

import co.com.education.database.jpa.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {
}
