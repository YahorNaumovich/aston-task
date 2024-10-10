package com.example.astontask.repository;

import com.example.astontask.model.Locality;
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
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class LocalityRepositoryTest {

    @Container
    private static final PostgreSQLContainer<?> postgreSQLContainer =
            new PostgreSQLContainer<>(DockerImageName.parse("postgres:latest"))
                    .withDatabaseName("testdb")
                    .withUsername("testuser")
                    .withPassword("testpass");

    @DynamicPropertySource
    static void dynamicProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgreSQLContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgreSQLContainer::getUsername);
        registry.add("spring.datasource.password", postgreSQLContainer::getPassword);
    }

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
    @DisplayName("Test saving a new locality")
    void whenSaveLocality_thenLocalityIsSaved() {

        Locality locality = new Locality();
        locality.setName("Test Locality");
        locality.setRegion("Test Region");

        Locality savedLocality = localityRepository.save(locality);

        assertNotNull(savedLocality.getId(), "The saved locality should have an ID");
        assertEquals("Test Locality", savedLocality.getName());
        assertEquals("Test Region", savedLocality.getRegion());

        Optional<Locality> foundLocality = localityRepository.findById(savedLocality.getId());
        assertTrue(foundLocality.isPresent());
        assertEquals("Test Locality", foundLocality.get().getName());

    }

}
