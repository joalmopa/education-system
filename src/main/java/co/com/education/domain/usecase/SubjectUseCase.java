package co.com.education.domain.usecase;


import co.com.education.domain.entity.Subject;
import co.com.education.domain.gateway.SubjectService;

import java.util.List;


public class SubjectUseCase {

    private SubjectService subjectService;

    public SubjectUseCase(SubjectService subjectService) {
        this.subjectService = subjectService;
    }


    public List<Subject> findSubjects() {
        return subjectService.getSubjects();
    }

    public Subject findSubjectById(Integer subjectId) {
        return subjectService.getSubjectById(subjectId);
    }

    public Subject saveOrUpdateSubject(Subject subject) {
        return subjectService.saveOrUpdateSubject(subject);
    }

    public void deleteSubject(Integer subjectId) {
        subjectService.deleteSubject(subjectId);
    }
}