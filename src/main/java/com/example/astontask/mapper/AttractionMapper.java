package com.example.astontask.mapper;

import com.example.astontask.dto.AttractionDTO;
import com.example.astontask.model.Attraction;

import java.util.List;
import java.util.stream.Collectors;

public class AttractionMapper {

    // Преобразование из Entity в DTO
    public static AttractionDTO toDto(Attraction attraction) {
        if (attraction == null) {
            return null;
        }

        AttractionDTO attractionDTO = new AttractionDTO();
        attractionDTO.setId(attraction.getId());
        attractionDTO.setName(attraction.getName());
        attractionDTO.setCreationDate(attraction.getCreationDate());
        attractionDTO.setDescription(attraction.getDescription());
        attractionDTO.setType(attraction.getType());

        if (attraction.getLocality() != null) {
            attractionDTO.setLocality(LocalityMapper.toDto(attraction.getLocality()));
        }

        if (attraction.getAssistance() != null) {
            attractionDTO.setAssistance(attraction.getAssistance().stream()
                    .map(AssistanceMapper::toDto)
                    .collect(Collectors.toList()));
        }

        return attractionDTO;
    }

    // Преобразование из DTO в Entity
    public static Attraction toEntity(AttractionDTO attractionDTO) {
        if (attractionDTO == null) {
            return null;
        }

        Attraction attraction = new Attraction();
        attraction.setId(attractionDTO.getId());
        attraction.setName(attractionDTO.getName());
        attraction.setCreationDate(attractionDTO.getCreationDate());
        attraction.setDescription(attractionDTO.getDescription());
        attraction.setType(attractionDTO.getType());

        if (attractionDTO.getLocality() != null) {
            attraction.setLocality(LocalityMapper.toEntity(attractionDTO.getLocality()));
        }

        if (attractionDTO.getAssistance() != null) {
            attraction.setAssistance(attractionDTO.getAssistance().stream()
                    .map(AssistanceMapper::toEntity)
                    .collect(Collectors.toList()));
        }

        return attraction;
    }

    // Преобразование списка Entity в список DTO
    public static List<AttractionDTO> toDtoList(List<Attraction> attractions) {
        return attractions.stream()
                .map(AttractionMapper::toDto)
                .collect(Collectors.toList());
    }

    // Преобразование списка DTO в список Entity
    public static List<Attraction> toEntityList(List<AttractionDTO> attractionDTOs) {
        return attractionDTOs.stream()
                .map(AttractionMapper::toEntity)
                .collect(Collectors.toList());
    }
}


