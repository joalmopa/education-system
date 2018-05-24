package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.StudentEntity;
import co.com.education.database.entity.TeacherEntity;
import co.com.education.database.repository.TeacherRepository;
import co.com.education.domain.entity.Student;
import co.com.education.domain.entity.Teacher;
import co.com.education.domain.gateway.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class TeacherGatewayImpl implements TeacherService {

    @Autowired
    TeacherRepository teacherRepository;

    @Override
    public List<Teacher> getTeachers() {
        return  teacherRepository.findAll().stream().map(this::toCore).collect(Collectors.toList());
    }

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return toCore(teacherRepository.findOne(teacherId));
    }

    @Override
    public Teacher saveOrUpdateTeacher(Teacher teacher) {

        return toCore(teacherRepository.save(toEntity(teacher)));
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherRepository.delete(teacherId);

    }

    public Teacher toCore(TeacherEntity entity) {

        return Teacher.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .documentNumber(entity.getDocumentNumber())
                .documentType(entity.getDocumentType())
                .birthDate(entity.getBirthDate())
                .phone(entity.getPhone())
                .build();
    }

    public TeacherEntity toEntity(Teacher core) {

        return new TeacherEntity(core.getId(), core.getDocumentType(), core.getDocumentNumber(), core.getName(),
                core.getPhone(),core.getEmail(),core.getBirthDate());
    }
}
