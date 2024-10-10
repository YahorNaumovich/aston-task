package com.example.astontask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"name", "region"})
public class LocalityCreateDTO {

    @Schema(example = "Moscow", maxLength = 255)
    private String name;

    @Schema(example = "Moscow Oblast", maxLength = 255)
    private String region;

}
