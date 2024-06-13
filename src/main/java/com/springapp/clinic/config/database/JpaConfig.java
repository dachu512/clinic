package com.springapp.clinic.config.database;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory",
        basePackages = {"com.springapp.clinic.repository", "com.springapp.clinic.model.entity"})
@Configuration
public class JpaConfig {

}
