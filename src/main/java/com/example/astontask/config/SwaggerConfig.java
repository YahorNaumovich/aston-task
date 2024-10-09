package com.example.astontask.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * Swagger configuration class for API documentation.
 * Configures Swagger to generate OpenAPI 3.0 documentation for the application.
 */
@Configuration
public class SwaggerConfig {

    /**
     * Creates and configures a Docket bean for Swagger.
     *
     * @return a Docket instance configured for OpenAPI 3.0 specification
     */
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.astontask"))
                .paths(PathSelectors.any())
                .build();

    }

}