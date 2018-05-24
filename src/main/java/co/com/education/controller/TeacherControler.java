package co.com.education.controller;



import co.com.education.domain.entity.Teacher;
import co.com.education.domain.usecase.TeacherUseCase;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/education")
@Api(description = "Endpoints for Teachers")
public class TeacherControler {

    @Autowired
    TeacherUseCase teacherUseCase;

    @GetMapping(value = "/teacher")
    @ApiOperation("return all teachers")
    public ResponseEntity<List<Teacher>> getTeachers() {

        List<Teacher> teachers = teacherUseCase.findTeachers();
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }

    @PostMapping(value = "/teacher")
    @ApiOperation("Save a teacher")
    public ResponseEntity<Teacher> save(@RequestBody Teacher teacher){
        return new ResponseEntity<>(teacherUseCase.saveOrUpdateTeacher(teacher), HttpStatus.CREATED);
    }
}
