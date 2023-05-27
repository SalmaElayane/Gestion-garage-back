package com.cigma.gg.controllers.bachoffice.dto;


import com.cigma.gg.entity.backoffice.MaintenenceEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class MaintenenceDto implements Serializable {

    private long id;
    private String reference;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateDebut;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateFin;
    private String etat;
    private String suivi;
    private String description;
    private VoitureDto voitureDto;

    /**
     * @param maintenenceEntity
     * @return
     */
    public static MaintenenceDto entityToDto(MaintenenceEntity maintenenceEntity) {
        MaintenenceDto maintenenceDto = new MaintenenceDto();
        if (maintenenceEntity != null) {
            maintenenceDto.setId(maintenenceEntity.getId());
            maintenenceDto.setReference(maintenenceEntity.getReference());
            maintenenceDto.setDateDebut(maintenenceEntity.getDateDebut());
            maintenenceDto.setDateFin(maintenenceEntity.getDateFin());
            maintenenceDto.setEtat(maintenenceEntity.getEtat());
            maintenenceDto.setSuivi(maintenenceEntity.getSuivi());
            maintenenceDto.setDescription(maintenenceEntity.getDescription());
            maintenenceDto.setVoitureDto(VoitureDto.entityToDto(maintenenceEntity.getVoitureEntity()));
        }
        return maintenenceDto;
    }

    /**
     * @param maintenenceDto
     * @return
     */
    public static MaintenenceEntity dtoToEntity(MaintenenceDto maintenenceDto) {
        MaintenenceEntity maintenenceEntity = new MaintenenceEntity();
        if (maintenenceDto != null) {
            maintenenceEntity.setId(maintenenceDto.getId());
            maintenenceEntity.setReference(maintenenceDto.getReference());
            maintenenceEntity.setDateDebut(maintenenceDto.getDateDebut());
            maintenenceEntity.setDateFin(maintenenceDto.getDateFin());
            maintenenceEntity.setEtat(maintenenceDto.getEtat());
            maintenenceEntity.setSuivi(maintenenceDto.getSuivi());
            maintenenceEntity.setDescription(maintenenceDto.getDescription());
            maintenenceEntity.setVoitureEntity(VoitureDto.dtoToEntity(maintenenceDto.getVoitureDto()));
        }
        return maintenenceEntity;
    }

    /**
     * @param maintenenceEntities
     * @return
     */
    public static List<MaintenenceDto> entitiesToDtos(List<MaintenenceEntity> maintenenceEntities) {
        List<MaintenenceDto> maintenenceDtos = new ArrayList<>();
        if (!maintenenceEntities.isEmpty()) {
            maintenenceDtos = maintenenceEntities.stream().map(MaintenenceDto::entityToDto).collect(Collectors.toList());
        }
        return maintenenceDtos;
    }
}
