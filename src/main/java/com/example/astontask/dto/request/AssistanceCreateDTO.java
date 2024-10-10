package com.example.astontask.dto.request;

import com.example.astontask.model.type.AssistanceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(requiredProperties = {"type", "provider"})
public class AssistanceCreateDTO {

    @Schema(example = "FOOD", maxLength = 255)
    private AssistanceType type;

    @Schema(example = "Providing hot meals and beverages to tourists during their visit", maxLength = 500)
    private String description;

    @Schema(example = "Moscow Food Services", maxLength = 255)
    private String provider;

}
