package com.example.astontask.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Entity representing a locality.
 * This class holds the details of a locality, including its name, region,
 * and the list of attractions that belong to this locality.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Locality {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String region;

    @OneToMany(mappedBy = "locality")
    @JsonIgnore
    private List<Attraction> attractions;
}
