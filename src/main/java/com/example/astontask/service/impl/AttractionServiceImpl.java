package com.example.astontask.service.impl;

import com.example.astontask.model.Assistance;
import com.example.astontask.model.Attraction;
import com.example.astontask.repository.AssistanceRepository;
import com.example.astontask.repository.AttractionRepository;
import com.example.astontask.service.AttractionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class AttractionServiceImpl implements AttractionService {

    private final AttractionRepository attractionRepository;
    private final AssistanceRepository assistanceRepository;

    @Override
    public void addAttraction(Attraction attraction) {
        attractionRepository.save(attraction);
        assistanceRepository.saveAll(attraction.getAssistance());
    }
}
