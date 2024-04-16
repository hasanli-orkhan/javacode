package ru.domain.javacode;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class LiquibaseConfiguration {

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:db/changelog/db.changelog-master.yml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        String url = System.getenv("SPRING_DATASOURCE_URL");
        String username = System.getenv("SPRING_DATASOURCE_USERNAME");
        String password = System.getenv("SPRING_DATASOURCE_PASSWORD");

        return DataSourceBuilder.create()
                .url("jdbc:postgresql://db:5432/javacode")
                .username("postgres")
                .password("postgres")
                .build();
    }
}
