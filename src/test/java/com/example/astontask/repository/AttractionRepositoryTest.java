package com.example.astontask.repository;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.model.type.AttractionType;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
@Transactional
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class AttractionRepositoryTest {

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
    private AttractionRepository attractionRepository;

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
    @DisplayName("Test saving a new attraction")
    void whenSaveAttraction_thenAttractionIsSaved() {

        Locality locality = new Locality(1L, "Test Locality", "Test Region", null);
        Attraction attraction = new Attraction(null, "Test Attraction", new Date(), "Description 1", AttractionType.PARK, locality, null);

        localityRepository.save(locality);
        Attraction savedAttraction = attractionRepository.save(attraction);

        assertNotNull(savedAttraction.getId(), "The saved attraction should have an ID");
        assertEquals("Test Attraction", savedAttraction.getName());
        assertEquals(AttractionType.PARK, savedAttraction.getType());

    }

    @Test
    @Order(3)
    @DisplayName("Test finding attractions by locality")
    void whenFindByLocality_thenAttractionsAreFound() {

        Locality locality = new Locality(null, "Test Locality", "Test Region", null);
        Locality savedLocality = localityRepository.save(locality);

        attractionRepository.save(new Attraction(null, "Attraction 1", new Date(), "Description 1", AttractionType.PARK, savedLocality, null));
        attractionRepository.save(new Attraction(null, "Attraction 2", new Date(), "Description 2", AttractionType.MUSEUM, savedLocality, null));

        List<Attraction> attractions = attractionRepository.findByLocality(locality);

        assertFalse(attractions.isEmpty(), "Attractions should be found for the given locality");
        assertEquals(2, attractions.size(), "There should be two attractions for this locality");

    }

    @Test
    @Order(4)
    @DisplayName("Test finding attractions by ID")
    void whenFindById_thenAttractionIsFound() {

        Locality locality = new Locality(null, "Test Locality", "Test Region", null);
        Locality savedLocality = localityRepository.save(locality);
        Attraction savedAttraction = attractionRepository.save(new Attraction(null, "Attraction 1", new Date(), "Description 1", AttractionType.PARK, savedLocality, null));

        Optional<Attraction> foundAttraction = attractionRepository.findById(savedAttraction.getId());

        assertTrue(foundAttraction.isPresent(), "Attraction should be found by ID");
        assertEquals(savedAttraction.getName(), foundAttraction.get().getName(), "The found attraction name should match");

    }

    @Test
    @Order(5)
    @DisplayName("Test deleting attraction by ID")
    void whenDeleteById_thenAttractionIsDeleted() {

        Locality locality = new Locality(null, "Test Locality", "Test Region", null);
        Locality savedLocality = localityRepository.save(locality);
        Attraction savedAttraction = attractionRepository.save(new Attraction(null, "Attraction 1", new Date(), "Description 1", AttractionType.PARK, savedLocality, null));

        attractionRepository.deleteById(savedAttraction.getId());

        Optional<Attraction> foundAttraction = attractionRepository.findById(savedAttraction.getId());
        assertFalse(foundAttraction.isPresent(), "Attraction should be deleted");

    }

    @Test
    @Order(6)
    @DisplayName("Test finding attractions by type")
    void whenFindByType_thenAttractionsAreFound() {

        Locality locality = new Locality(null, "Test Locality", "Test Region", null);
        Locality savedLocality = localityRepository.save(locality);
        attractionRepository.save(new Attraction(null, "Attraction 1", new Date(), "Description 1", AttractionType.PARK, savedLocality, null));
        attractionRepository.save(new Attraction(null, "Attraction 2", new Date(), "Description 2", AttractionType.MUSEUM, savedLocality, null));

        List<Attraction> parkAttractions = attractionRepository.findByType(AttractionType.PARK, Sort.by("name"));

        assertFalse(parkAttractions.isEmpty(), "Attractions should be found for the given type");
        assertEquals(1, parkAttractions.size(), "There should be one park attraction");
        assertEquals("Attraction 1", parkAttractions.get(0).getName(), "The park attraction should have the correct name");

    }


}
