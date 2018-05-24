package co.com.education.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan({"co.com.education.controller"})
@EntityScan("co.com.education.database.entity")
@EnableJpaRepositories("co.com.education.database.repository")

@Import({ DomainBeans.class})
@EnableConfigurationProperties
public class EducationSystemApplication  extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(EducationSystemApplication.class, args);
	}
}
