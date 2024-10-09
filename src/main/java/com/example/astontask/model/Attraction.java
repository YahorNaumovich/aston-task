package com.example.astontask.model;

import com.example.astontask.model.type.AttractionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

/**
 * Entity representing an attraction.
 * This class holds the details of an attraction, including its name, creation date, description, type, locality, and related assistance services.
 */
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Attraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Date creationDate;
    private String description;

    @Enumerated(EnumType.STRING)
    private AttractionType type;

    @ManyToOne
    @JoinColumn(name = "locality_id")
    private Locality locality;

    @ManyToMany
    @JoinTable(
            name = "attraction_assistance",
            joinColumns = @JoinColumn(name = "attraction_id"),
            inverseJoinColumns = @JoinColumn(name = "assistance_id"))
    private List<Assistance> assistance;
}
