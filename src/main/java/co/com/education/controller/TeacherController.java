package co.com.education.controller;


import co.com.education.domain.entity.Student;
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
public class TeacherController {

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

    @GetMapping(value = "/teacher/{teacherId}")
    @ApiOperation("return a teacher by Id")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable("teacherId") Integer teacherId) {
        return new ResponseEntity<>(teacherUseCase.findTeacherById(teacherId), HttpStatus.OK);
    }

    @DeleteMapping(value = "/teacher/{teacherId}")
    @ApiOperation("Delete a teacher by Id")
    public ResponseEntity<?> deleteTeacher(@PathVariable("teacherId") Integer teacherId) {
        teacherUseCase.deleteTeacher(teacherId);
        return new ResponseEntity<Teacher>(HttpStatus.NO_CONTENT);
    }

    @GetMapping(value = "/teacher/gender/{gender}")
    @ApiOperation("return list of teachers by gender")
    public ResponseEntity<List<Teacher>> getTeacherByGender(@PathVariable("gender") String gender){
        List<Teacher> teachers = teacherUseCase.findTeachersByGender(gender);
        return new ResponseEntity<>(teachers, HttpStatus.OK);
    }
}
