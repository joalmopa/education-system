package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.StudentEntity;
import co.com.education.database.repository.StudentRepository;
import co.com.education.domain.entity.Student;
import co.com.education.domain.gateway.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class StudentGatewayImpl implements StudentService{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<Student> getStudents() {

        return  studentRepository.findAll().stream().map(this::toCore).collect(Collectors.toList());
    }

    @Override
    public Student getStudentById(Integer studentId) {

        return toCore(studentRepository.findOne(studentId));
    }

    @Override
    public Student saveOrUpdateStudent(Student student) {

        return toCore(studentRepository.save(toEntity(student)));
    }

    @Override
    public void deleteStudent(Integer studentId) {
        studentRepository.delete(studentId);

    }

    public Student toCore(StudentEntity entity) {

        return Student.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .documentType(entity.getDocumentType())
                .documentNumber(entity.getDocumentNumber())
                .birthDate(entity.getBirthDate())
                .build();
    }

    public StudentEntity toEntity(Student core) {

        return new StudentEntity(core.getId(), core.getDocumentType(), core.getDocumentNumber(), core.getName(),
                core.getPhone(),core.getEmail(),core.getBirthDate());
    }
}
