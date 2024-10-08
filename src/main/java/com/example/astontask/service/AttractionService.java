package com.example.astontask.service;

import com.example.astontask.model.Attraction;

import java.util.List;

public interface AttractionService {
    void addAttraction(Attraction attraction);

    List<Attraction> getAllAttractionsByLocality(Long localityId);
}
