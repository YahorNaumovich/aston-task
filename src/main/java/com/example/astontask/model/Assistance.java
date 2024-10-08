package com.example.astontask.model;

import com.example.astontask.model.type.AssistanceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Assistance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private AssistanceType type;

    private String description;
    private String provider;

    @ManyToMany(mappedBy = "assistance")
    private List<Attraction> attractions;
}