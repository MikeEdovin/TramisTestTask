package Repositories;

import Configs.DBConfig;
import Entities.Declaration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.sql.Date;

@DataJpaTest()
@Testcontainers
@ContextConfiguration(classes = DBConfig.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeclarationRepositoryTests {
    @Container
    static PostgreSQLContainer database = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("testDb")
            .withPassword("password")
            .withUsername("user");
    @DynamicPropertySource
    static void setDatasourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url", database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password", database::getPassword);
        propertyRegistry.add("spring.datasource.username", database::getUsername);
    }
    void setUp() {
        Declaration declaration = new Declaration();
        declaration.setDeclarationNumber("10702070/100720/0152359");
        declaration.setDateOfFilling(Date.valueOf("2020-07-10"));
        declaration.setDateOfIssue(Date.valueOf("2020-07-14"));
        declaration.setBroker("Контракт клиента");
        repository.save(declaration);
    }

    @Autowired
    DeclarationRepository repository;

    @Test
    void findByDeclarationNumberShouldReturnExpectedValue(){
        setUp();
        Declaration declaration=repository.findByDeclarationNumber("10702070/100720/0152359");
        Assertions.assertNotNull(declaration);
        Assertions.assertEquals("Контракт клиента",declaration.getBroker());
        repository.deleteAll();
    }
}
