package com.example.astontask.dto.request;

import com.example.astontask.model.type.AssistanceType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AssistanceCreateDTO {

    @Schema(example = "FOOD")
    private AssistanceType type;

    @Schema(example = "Providing hot meals and beverages to tourists during their visit")
    private String description;

    @Schema(example = "Moscow Food Services")
    private String provider;

}
