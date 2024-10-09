package com.example.astontask.repository;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.model.type.AttractionType;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for accessing {@link Attraction} entities.
 * Provides methods to perform CRUD operations and custom queries on the `attraction` table.
 */
@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {

    /**
     * Finds all attractions by type and sorts the results.
     *
     * @param type the type of the attraction (e.g., museum, park, etc.)
     * @param sort the sorting criteria to order the results
     * @return a list of attractions matching the specified type, sorted according to the given criteria
     */
    List<Attraction> findByType(AttractionType type, Sort sort);

    /**
     * Finds all attractions by locality.
     *
     * @param locality the locality in which the attractions are located
     * @return a list of attractions associated with the specified locality
     */
    List<Attraction> findByLocality(Locality locality);

}
