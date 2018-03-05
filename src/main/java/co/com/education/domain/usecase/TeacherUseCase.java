package co.com.education.domain.usecase;

import co.com.education.domain.gateway.TeacherService;


public class TeacherUseCase {

    private TeacherService teacherService;

    public TeacherUseCase(TeacherService teacherService) {
        this.teacherService = teacherService;
    }
}
