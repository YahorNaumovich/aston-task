package com.example.astontask.repository;

import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.model.type.AttractionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttractionRepository extends JpaRepository<Attraction, Long> {
    List<Attraction> findByType(AttractionType type);
    List<Attraction> findByLocality(Locality locality);
}
