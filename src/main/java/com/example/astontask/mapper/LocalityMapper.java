package com.example.astontask.mapper;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.model.Locality;

import java.util.List;
import java.util.stream.Collectors;

public class LocalityMapper {

    // Преобразование из Entity в DTO
    public static LocalityDTO toDto(Locality locality) {
        if (locality == null) {
            return null;
        }

        LocalityDTO localityDTO = new LocalityDTO();
        localityDTO.setId(locality.getId());
        localityDTO.setName(locality.getName());
        localityDTO.setRegion(locality.getRegion());

        if (locality.getAttractions() != null) {
            localityDTO.setAttractions(AttractionMapper.toDtoList(locality.getAttractions()));
        }

        return localityDTO;
    }

    // Преобразование из DTO в Entity
    public static Locality toEntity(LocalityDTO localityDTO) {
        if (localityDTO == null) {
            return null;
        }

        Locality locality = new Locality();
        locality.setId(localityDTO.getId());
        locality.setName(localityDTO.getName());
        locality.setRegion(localityDTO.getRegion());

        if (localityDTO.getAttractions() != null) {
            locality.setAttractions(AttractionMapper.toEntityList(localityDTO.getAttractions()));
        }

        return locality;
    }

    // Преобразование списка Entity в список DTO
    public static List<LocalityDTO> toDtoList(List<Locality> localities) {
        return localities.stream()
                .map(LocalityMapper::toDto)
                .collect(Collectors.toList());
    }

    // Преобразование списка DTO в список Entity
    public static List<Locality> toEntityList(List<LocalityDTO> localityDTOs) {
        return localityDTOs.stream()
                .map(LocalityMapper::toEntity)
                .collect(Collectors.toList());
    }
}

