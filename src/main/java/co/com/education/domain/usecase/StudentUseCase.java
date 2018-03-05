package co.com.education.domain.usecase;

import co.com.education.domain.gateway.StudentService;


public class StudentUseCase {

    private StudentService studentService;

    public StudentUseCase(StudentService studentService) {
        this.studentService = studentService;
    }
}
