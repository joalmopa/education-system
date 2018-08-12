package co.com.education.controller;

import co.com.education.domain.entity.Subject;
import co.com.education.domain.usecase.SubjectUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/education")
public class SubjectController {

    @Autowired
    private SubjectUseCase subjectUseCase;

    @GetMapping(value = "/subjects")
    public ResponseEntity<List<Subject>> getSubjects() {

        List<Subject> subjects = subjectUseCase.findSubjects();
        return new ResponseEntity<>(subjects, HttpStatus.OK);
    }

    @GetMapping(value = "/subject/{subjectId}")
    public ResponseEntity<Subject> getSubjectById(@PathVariable("subjectId") Integer subjectId) {
        Subject subject = subjectUseCase.findSubjectById(subjectId);
        return new ResponseEntity<>(subject, ((subject != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND));
    }

    @PostMapping(value="/subject")
    public ResponseEntity<Subject> save(@RequestBody Subject subject) {
        return new ResponseEntity<> (subjectUseCase.saveOrUpdateSubject(subject), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/subject/{subjectId}")
    public ResponseEntity<?> deleteSubject(@PathVariable("subjectId") Integer subjectId) {
        subjectUseCase.deleteSubject(subjectId);
        return new ResponseEntity<Subject>(HttpStatus.OK);

    }
}