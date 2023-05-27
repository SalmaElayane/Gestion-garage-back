package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.FactureEntity;
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
public class FactureDto implements Serializable {
    private long id;
    private String reference;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date date;
    private String montant;
    private MaintenenceDto maintenenceDto;

    /**
     * @param factureEntity
     * @return
     */
    public static FactureDto entityToDto(FactureEntity factureEntity) {
        FactureDto factureDto = new FactureDto();
        if (factureEntity != null) {
            factureDto.setId(factureEntity.getId());
            factureDto.setReference(factureEntity.getReference());
            factureDto.setDate(factureEntity.getDate());
            factureDto.setMontant(factureEntity.getMontant());
            factureDto.setMaintenenceDto(MaintenenceDto.entityToDto(factureEntity.getMaintenenceEntity()));
        }
        return factureDto;
    }

    /**
     * @param factureDto
     * @return
     */
    public static FactureEntity dtoToEntity(FactureDto factureDto) {
        FactureEntity factureEntity = new FactureEntity();
        if (factureDto != null) {
            factureEntity.setId(factureDto.getId());
            factureEntity.setReference(factureDto.getReference());
            factureEntity.setDate(factureDto.getDate());
            factureEntity.setMontant(factureDto.getMontant());
            factureEntity.setMaintenenceEntity(MaintenenceDto.dtoToEntity(factureDto.getMaintenenceDto()));
        }
        return factureEntity;
    }

    /**
     * @param factureEntities
     * @return
     */
    public static List<FactureDto> entitiesToDtos(List<FactureEntity> factureEntities) {
        List<FactureDto> factureDtos = new ArrayList<>();
        if (!factureEntities.isEmpty()) {
            factureDtos = factureEntities.stream().map(FactureDto::entityToDto).collect(Collectors.toList());
        }
        return factureDtos;
    }
}
