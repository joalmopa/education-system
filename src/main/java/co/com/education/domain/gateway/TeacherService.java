package co.com.education.domain.gateway;

import co.com.education.domain.entity.Teacher;

import java.util.List;

public interface TeacherService {

    List<Teacher> getTeachers();

    Teacher getTeacherById(Integer teacherId);

    Teacher saveOrUpdateTeacher(Teacher teacher);

    void deleteTeacher(Integer teacherId);

    List<Teacher> getTeachersByGender(String gender);



}
