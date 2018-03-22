package co.com.education.config;

import co.com.education.database.repositoryimpl.CourseRepositoryImpl;
import co.com.education.domain.gateway.CourseService;
import co.com.education.domain.usecase.CourseUseCase;
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
        return new CourseRepositoryImpl();
    }


    @Bean
    public CourseUseCase courseUseCase(CourseService courseService) {
        return new CourseUseCase(courseService);
    }
}
