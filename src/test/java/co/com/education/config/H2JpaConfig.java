package co.com.education.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@EnableTransactionManagement
@EntityScan("co.com.education.database.entity")
@PropertySource("classpath:/application-test.properties")
@ComponentScan({"co.com.education.database.gatewayimpl"})
@EnableJpaRepositories("co.com.education.database.repository")
@Import({ DomainBeans.class})
public class H2JpaConfig {

    @Autowired
    private Environment env;

    @Bean
    @Profile("test")
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("database.h2.driverClassName"));
        dataSource.setUrl(env.getProperty("database.h2.url"));
        dataSource.setUsername(env.getProperty("database.h2.username"));
        dataSource.setPassword(env.getProperty("database.h2.password"));

        return dataSource;
    }

    @Bean(name = "entityManagerFactory")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(dataSource());
        em.setPackagesToScan(new String[] { "co.com.education.database.entity", "co.com.education.database.gatewayimpl" });
        em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        em.setJpaProperties(additionalProperties());

        return em;
    }

    @Bean(name = "transactionManager")
    JpaTransactionManager jpaTransactionManager(final EntityManagerFactory entityManagerFactory) {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(entityManagerFactory);

        return transactionManager;
    }


    final Properties additionalProperties() {
        final Properties hibernateProperties = new Properties();

        hibernateProperties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("database.h2.ddl-auto"));
        hibernateProperties.setProperty("hibernate.dialect", env.getProperty("database.h2.dialect"));
        hibernateProperties.setProperty("hibernate.show_sql", env.getProperty("database.h2.show-sql"));

        return hibernateProperties;
    }
}
