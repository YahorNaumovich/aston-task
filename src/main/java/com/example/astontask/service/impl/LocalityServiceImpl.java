package com.example.astontask.service.impl;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.mapper.LocalityMapper;
import com.example.astontask.model.Locality;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.LocalityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;

    @Override
    public void addLocality(Locality locality) {
        localityRepository.save(locality);
    }
}
