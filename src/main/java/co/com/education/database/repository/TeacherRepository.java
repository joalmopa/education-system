package co.com.education.database.repository;

import co.com.education.database.entity.TeacherEntity;
import org.springframework.data.repository.CrudRepository;

public interface TeacherRepository extends CrudRepository<TeacherEntity, Integer> {
}
