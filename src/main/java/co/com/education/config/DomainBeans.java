package co.com.education.config;

import co.com.education.database.gatewayimpl.SubjectGatewayImpl;
import co.com.education.database.gatewayimpl.GroupGatewayImpl;
import co.com.education.database.gatewayimpl.StudentGatewayImpl;
import co.com.education.database.gatewayimpl.TeacherGatewayImpl;
import co.com.education.domain.gateway.SubjectService;
import co.com.education.domain.gateway.GroupService;
import co.com.education.domain.gateway.StudentService;
import co.com.education.domain.gateway.TeacherService;
import co.com.education.domain.usecase.SubjectUseCase;
import co.com.education.domain.usecase.GroupUseCase;
import co.com.education.domain.usecase.StudentUseCase;
import co.com.education.domain.usecase.TeacherUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DomainBeans {

    @Autowired
    ApplicationContext context;


    @Bean
    public SubjectService subjectService() {
        return new SubjectGatewayImpl();
    }

    @Bean
    public GroupService groupService() {
        return new GroupGatewayImpl();
    }


    @Bean
    public SubjectUseCase subjectUseCase(SubjectService subjectService) {
        return new SubjectUseCase(subjectService);
    }

    @Bean
    public GroupUseCase groupUseCase(GroupService groupService) {
        return new GroupUseCase(groupService);
    }

    @Bean
    public StudentService  studentService(){return new StudentGatewayImpl();}

    @Bean
    public StudentUseCase studentUseCase(StudentService studentService){return new StudentUseCase(studentService);}


    @Bean
    public TeacherService teacherService(){return new TeacherGatewayImpl();}

    @Bean
    public TeacherUseCase teacherUseCase(TeacherService teacherService){return new TeacherUseCase(teacherService); }

}

