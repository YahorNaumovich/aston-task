package com.example.astontask.repository;

import com.example.astontask.model.Assistance;
import com.example.astontask.model.type.AssistanceType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AssistanceRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");
    @Autowired
    private LocalityRepository localityRepository;

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private AssistanceRepository assistanceRepository;

    @Test
    @Order(1)
    @DisplayName("Ensure repository connects to the Testcontainers database")
    void whenConnectToDatabase_thenUrlIsFromTestcontainers() throws SQLException {

        String currentDbUrl = Objects.requireNonNull(jdbcTemplate.getDataSource()).getConnection().getMetaData().getURL();

        String expectedDbUrl = postgreSQLContainer.getJdbcUrl();

        assertEquals(currentDbUrl, expectedDbUrl, "Connected to the wrong database: " + currentDbUrl);
    }

    @Test
    @Order(2)
    @DisplayName("Test saving all assistances")
    void whenSaveAllAssistances_thenAllAssistancesAreSaved() {

        Assistance assistance1 = new Assistance(null, AssistanceType.FOOD, "Description 1", "Provider 1", null);
        Assistance assistance2 = new Assistance(null, AssistanceType.CAR_TOUR, "Description 2", "Provider 2", null);
        Assistance assistance3 = new Assistance(null, AssistanceType.GUIDE, "Description 3", "Provider 3", null);

        List<Assistance> savedAssistances = assistanceRepository.saveAll(List.of(assistance1, assistance2, assistance3));

        assertAll("Assistance saveAll validation",
                () -> savedAssistances.forEach(assistance -> {
                    assertNotNull(assistance.getId(), "The saved assistance should have an ID");
                })
        );

    }
}
