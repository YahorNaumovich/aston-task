package com.example.astontask.mapper;

import com.example.astontask.dto.AssistanceDTO;
import com.example.astontask.model.Assistance;

public class AssistanceMapper {

    // Преобразование из Entity в DTO
    public static AssistanceDTO toDto(Assistance assistance) {
        if (assistance == null) {
            return null;
        }

        AssistanceDTO assistanceDTO = new AssistanceDTO();
        assistanceDTO.setId(assistance.getId());
        assistanceDTO.setType(assistance.getType());
        assistanceDTO.setDescription(assistance.getDescription());
        assistanceDTO.setProvider(assistance.getProvider());

        return assistanceDTO;
    }

    // Преобразование из DTO в Entity
    public static Assistance toEntity(AssistanceDTO assistanceDTO) {
        if (assistanceDTO == null) {
            return null;
        }

        Assistance assistance = new Assistance();
        assistance.setId(assistanceDTO.getId());
        assistance.setType(assistanceDTO.getType());
        assistance.setDescription(assistanceDTO.getDescription());
        assistance.setProvider(assistanceDTO.getProvider());

        return assistance;
    }
}

