package com.example.astontask.repository;

import com.example.astontask.model.Locality;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LocalityRepository extends JpaRepository<Locality, Long> {
    List<Locality> findByName(String name);
}
