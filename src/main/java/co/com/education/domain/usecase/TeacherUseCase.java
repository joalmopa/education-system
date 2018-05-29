package co.com.education.domain.usecase;

import co.com.education.domain.entity.Teacher;
import co.com.education.domain.gateway.TeacherService;

import java.util.List;


public class TeacherUseCase {

    private TeacherService teacherService;

    public TeacherUseCase(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    public List<Teacher> findTeachers(){return teacherService.getTeachers();}

    public Teacher findTeacherById(Integer teacherId){ return teacherService.getTeacherById(teacherId);}

    public Teacher saveOrUpdateTeacher(Teacher teacher) {
        return teacherService.saveOrUpdateTeacher(teacher);
    }

    public void deleteTeacher(Integer teacherID) {
        teacherService.deleteTeacher(teacherID);
    }

}
