package co.com.education.controller;



import co.com.education.domain.entity.Student;
import co.com.education.domain.usecase.StudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
public class StudentController {

    @Autowired
    StudentUseCase studentUseCase;

    @GetMapping(value = "/student")
    public ResponseEntity<List<Student>> getStudents() {

        List<Student> students = studentUseCase.findStudents();
        return new ResponseEntity<>(students, HttpStatus.OK);
    }

    @PostMapping(value="/student")
    public ResponseEntity<Student> save(@RequestBody Student student) {
        return new ResponseEntity<> (studentUseCase.saveOrUpdateStudent(student), HttpStatus.CREATED);
    }
}
