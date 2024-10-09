package com.example.astontask.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for ModelMapper.
 * Provides a singleton ModelMapper bean to be used across the application for entity-DTO mappings.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Creates and configures a ModelMapper bean.
     *
     * @return a configured ModelMapper instance
     */
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
