package com.example.astontask.repository;

import com.example.astontask.model.Assistance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing {@link Assistance} entities.
 * Provides methods to perform CRUD operations on the `assistance` table.
 */
@Repository
public interface AssistanceRepository extends JpaRepository<Assistance, Long> {
}
