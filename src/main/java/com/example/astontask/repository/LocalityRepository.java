package com.example.astontask.repository;

import com.example.astontask.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing {@link Locality} entities.
 * Provides methods to perform CRUD operations on the `locality` table.
 */
@Repository
public interface LocalityRepository extends JpaRepository<Locality, Long> {
}
