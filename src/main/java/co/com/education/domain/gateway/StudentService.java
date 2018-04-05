package co.com.education.domain.gateway;

import co.com.education.domain.entity.Student;

import java.util.List;

public interface StudentService {

    List<Student> getStudents();

    Student getStudentById(Integer studentId);

    Student saveOrUpdateStudent(Student student);

    void deleteStudent(Integer studentId);
}


