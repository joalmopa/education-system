package co.com.education.domain.usecase;

import co.com.education.domain.entity.Student;
import co.com.education.domain.gateway.StudentService;

import java.util.List;


public class StudentUseCase {

    private StudentService studentService;

    public StudentUseCase(StudentService studentService) {
        this.studentService = studentService;
    }



    public List<Student> findStudents() {
        return studentService.getStudents();
    }

    public Student findStudentById(Integer studenId) {
        return studentService.getStudentById(studenId);
    }

    public Student saveOrUpdateStudent(Student student) {
        return studentService.saveOrUpdateStudent(student);
    }

    public void deleteStudent(Integer studentId) {
        studentService.deleteStudent(studentId);
    }
}
