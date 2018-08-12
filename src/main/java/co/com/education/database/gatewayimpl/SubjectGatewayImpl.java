package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.SubjectEntity;
import co.com.education.database.repository.SubjectRepository;
import co.com.education.domain.entity.Subject;
import co.com.education.domain.gateway.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class SubjectGatewayImpl implements SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;


    @Override
    public List<Subject> getSubjects() {
        return subjectRepository.findAll().stream()
                .map(this::toCore)
                .collect(Collectors.toList());
    }

    @Override
    public Subject getSubjectById(Integer subjectId) {
        return toCore(subjectRepository.findOne(subjectId));
    }

    @Override
    public Subject saveOrUpdateSubject(Subject subject) {
        return toCore(subjectRepository.save(toEntity(subject)));
    }

    @Override
    public void deleteSubject(Integer subjectId) {
        subjectRepository.delete(subjectId);
    }


    public Subject toCore(SubjectEntity entity) {
        return Subject.builder()
                .id(entity.getId())
                .description(entity.getDescription())
                .build();
    }

    public SubjectEntity toEntity(Subject core) {

        return new SubjectEntity(core.getId(), core.getDescription());
    }
}
