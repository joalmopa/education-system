package co.com.education.database.repository;

import co.com.education.database.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface StudentRepository extends JpaRepository<StudentEntity, Integer> {
}
