package co.com.education.database.repository;

import co.com.education.database.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SubjectRepository extends JpaRepository<SubjectEntity, Integer> {

}
