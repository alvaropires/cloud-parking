package one.digitalinnovation.parking.controller;

import net.bytebuddy.utility.dispatcher.JavaDispatcher;
import org.intellij.lang.annotations.JdkConstants;
import org.junit.ClassRule;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

@Component
public abstract class AbstractContainerBase {

    @ClassRule
    static final PostgreSQLContainer<?> POSTGRE_SQL_CONTAINER;


    static {
        POSTGRE_SQL_CONTAINER  = new PostgreSQLContainer("postgres:10-alpine");
        POSTGRE_SQL_CONTAINER.start();
        System.setProperty("spring.datasource.url", POSTGRE_SQL_CONTAINER.getJdbcUrl());
        System.setProperty("spring.datasource.driverClassName", POSTGRE_SQL_CONTAINER.getDriverClassName());
        System.setProperty("spring.datasource.username", POSTGRE_SQL_CONTAINER.getUsername());
        System.setProperty("spring.datasource.password", POSTGRE_SQL_CONTAINER.getPassword());
    }
    
    
}
