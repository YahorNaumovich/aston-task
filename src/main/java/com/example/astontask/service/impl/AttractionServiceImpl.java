package com.example.astontask.service.impl;

import com.example.astontask.model.Assistance;
import com.example.astontask.model.Attraction;
import com.example.astontask.model.Locality;
import com.example.astontask.repository.AssistanceRepository;
import com.example.astontask.repository.AttractionRepository;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.AttractionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AssistanceRepository assistanceRepository;
    private final LocalityRepository localityRepository;

    @Override
    public void addAttraction(Attraction attraction) {
        attractionRepository.save(attraction);
        assistanceRepository.saveAll(attraction.getAssistance());
    }

    @Override
    public List<Attraction> getAllAttractionsByLocality(Long localityId) {
        Locality locality = localityRepository.findById(localityId).orElseThrow(RuntimeException::new);
        return attractionRepository.findByLocality(locality);
    }

    @Override
    public void updateAttractionDescription(Long id, String description) {
        Attraction attractionToUpdate = attractionRepository.findById(id).orElseThrow(RuntimeException::new);
        attractionToUpdate.setDescription(description);
        attractionRepository.save(attractionToUpdate);
    }
}
