package co.com.education.config;

import co.com.education.database.gatewayimpl.CourseGatewayImpl;
import co.com.education.database.gatewayimpl.StudentGatewayImpl;
import co.com.education.domain.gateway.CourseService;
import co.com.education.domain.gateway.StudentService;
import co.com.education.domain.usecase.CourseUseCase;
import co.com.education.domain.usecase.StudentUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class DomainBeans {

    @Autowired
    ApplicationContext context;


    @Bean
    public CourseService courseService() {
        return new CourseGatewayImpl();
    }


    @Bean
    public CourseUseCase courseUseCase(CourseService courseService) {
        return new CourseUseCase(courseService);
    }

    @Bean
    public StudentService  studentService(){return new StudentGatewayImpl();}

    @Bean
    public StudentUseCase studentUseCase(StudentService studentService){return new StudentUseCase(studentService);}
}
