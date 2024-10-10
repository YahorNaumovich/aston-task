package com.example.astontask.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"id"})
public class AttractionLocalityReferenceDTO {

    @Schema(example = "1")
    private Long id;

}
