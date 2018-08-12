package co.com.education.domain.gateway;

import co.com.education.domain.entity.Subject;

import java.util.List;


public interface SubjectService {

    List<Subject> getSubjects();

    Subject getSubjectById(Integer subjectId);

    Subject saveOrUpdateSubject(Subject subject);

    void deleteSubject(Integer subject);
}
