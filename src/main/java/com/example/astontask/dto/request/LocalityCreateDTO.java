package com.example.astontask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalityCreateDTO {

    @Schema(example = "Moscow")
    private String name;

    @Schema(example = "Moscow Oblast")
    private String region;

}
