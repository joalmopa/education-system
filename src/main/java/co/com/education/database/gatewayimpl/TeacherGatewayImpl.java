package co.com.education.database.gatewayimpl;

import co.com.education.database.entity.TeacherEntity;
import co.com.education.database.repository.TeacherRepository;
import co.com.education.domain.entity.Teacher;
import co.com.education.domain.gateway.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
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
        return toCoreOptional(Optional.ofNullable(teacherRepository.findOne(teacherId)));
    }

    @Override
    public Teacher saveOrUpdateTeacher(Teacher teacher) {
        return toCoreOptional(Optional.ofNullable(teacherRepository.save(toEntity(teacher))));
    }

    @Override
    public void deleteTeacher(Integer teacherId) {teacherRepository.delete(teacherId);}

    @Override
    public List<Teacher> getTeachersByGender(String gender) {
         return  teacherRepository.findAll().stream().map(this::toCore)
                 .filter(t -> t.getGender().equals(gender))
                 .collect(Collectors.toList());
    }


    public Teacher toCoreOptional(Optional<TeacherEntity>  op){
        if(op.isPresent()){
            return Teacher.builder()
                    .id(op.get().getId())
                    .name(op.get().getName())
                    .email(op.get().getEmail())
                    .documentNumber(op.get().getDocumentNumber())
                    .documentType(op.get().getDocumentType())
                    .birthDate(op.get().getBirthDate())
                    .phone(op.get().getPhone())
                    .gender(op.get().getGender())
                    .build();

        }else{
            System.out.println(" ******* no hay datos *********** ");
            return null;
        }


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
                .gender(entity.getGender())
                .build();
    }

    public TeacherEntity toEntity(Teacher core) {

        return new TeacherEntity(core.getId(), core.getDocumentType(), core.getDocumentNumber(), core.getName(),
                core.getPhone(),core.getEmail(),core.getBirthDate(), core.getGender());
    }
}
