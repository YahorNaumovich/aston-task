package com.example.astontask.dto.request;

import com.example.astontask.model.type.AttractionType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AttractionCreateDTO {

    @Schema(example = "Moscow Kremlin")
    private String name;

    @Schema(example = "2024-10-10T19:47:42.032Z")
    private Date creationDate;

    @Schema(example = "Historic site located in the center of Moscow")
    private String description;

    @Schema(example = "PALACE")
    private AttractionType type;

    private AttractionLocalityReferenceDTO locality;

    @Schema(example = """
            [
                {
                    "type": "FOOD",
                    "description": "Providing hot meals and beverages to tourists during their visit",
                    "provider": "Moscow Food Services"
                },
                {
                    "type": "CAR_TOUR",
                    "description": "Providing transportation services to tourists, including shuttle buses and private rides.",
                    "provider": "Moscow Transport Services"
                }
            ]
            """)
    private List<AssistanceCreateDTO> assistance;

}
