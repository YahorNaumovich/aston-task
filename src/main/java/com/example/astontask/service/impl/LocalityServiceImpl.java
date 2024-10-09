package com.example.astontask.service.impl;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.model.Locality;
import com.example.astontask.repository.LocalityRepository;
import com.example.astontask.service.LocalityService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class LocalityServiceImpl implements LocalityService {

    private final LocalityRepository localityRepository;
    private final ModelMapper modelMapper;

    @Override
    public void addLocality(LocalityDTO localityDTO) {
        Locality locality = modelMapper.map(localityDTO, Locality.class);
        localityRepository.save(locality);
    }
}
