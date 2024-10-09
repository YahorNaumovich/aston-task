package com.example.astontask.service.impl;

import com.example.astontask.dto.AttractionDTO;
import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.model.type.AttractionType;
import com.example.astontask.repository.AssistanceRepository;
import com.example.astontask.repository.AttractionRepository;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.AttractionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AssistanceRepository assistanceRepository;
    private final LocalityRepository localityRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addAttraction(AttractionDTO attractionDTO) {
        Attraction attraction = modelMapper.map(attractionDTO, Attraction.class);
        attractionRepository.save(attraction);
        assistanceRepository.saveAll(attraction.getAssistance());
    }

    @Override
    public List<AttractionDTO> getAllAttractionsByLocality(Long localityId) {
        Locality locality = localityRepository.findById(localityId).orElseThrow(RuntimeException::new);
        List<Attraction> attractions = attractionRepository.findByLocality(locality);
        return attractions.stream().map(attraction -> modelMapper.map(attraction, AttractionDTO.class)).toList();
    }

    @Override
    public void updateAttractionDescription(Long id, String description) {
        Attraction attractionToUpdate = attractionRepository.findById(id).orElseThrow(RuntimeException::new);
        attractionToUpdate.setDescription(description);
        attractionRepository.save(attractionToUpdate);
    }

    @Override
    public void deleteAttraction(Long id) {
        Attraction attractionToDelete = attractionRepository.findById(id).orElseThrow(RuntimeException::new);
        attractionRepository.delete(attractionToDelete);
    }

    @Override
    public List<AttractionDTO> getAllAttractions(String sortBy, AttractionType type) {
        Sort sort = Sort.by(Sort.Order.asc(sortBy));
        List<Attraction> attractions = attractionRepository.findByType(type, sort);
        return attractions.stream().map(attraction -> modelMapper.map(attraction, AttractionDTO.class)).toList();
    }
}
