package co.com.education.database.jpa.repository;

import co.com.education.database.jpa.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
}
