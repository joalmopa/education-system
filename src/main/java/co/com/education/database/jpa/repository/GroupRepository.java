package co.com.education.database.jpa.repository;

import co.com.education.database.jpa.entity.GroupEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<GroupEntity, Integer> {
}