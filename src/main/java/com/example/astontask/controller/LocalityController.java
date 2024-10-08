package com.example.astontask.controller;

import com.example.astontask.dto.LocalityDTO;
import com.example.astontask.model.Locality;
import com.example.astontask.service.LocalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/localities")
@RequiredArgsConstructor
public class LocalityController {

    private final LocalityService localityService;

    @PostMapping
    public void addLocality(@RequestBody Locality locality){
        localityService.addLocality(locality);
    }
}
