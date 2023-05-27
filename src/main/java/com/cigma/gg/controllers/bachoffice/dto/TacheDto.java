package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.TacheEntity;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TacheDto implements Serializable {

    private long id ;
    private String reference;
    private String nom;
    private String service;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date date_debut;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date date_fin;
    private long status;
    private String description;
    private EmployeDto employeDto;
    /**
     * @param tacheEntity
     * @return
     */
    public static TacheDto entityToDto(TacheEntity tacheEntity) {
        TacheDto tacheDto = new TacheDto();
        if (tacheEntity != null) {
            tacheDto.setId(tacheEntity.getId());
            tacheDto.setReference(tacheEntity.getReference());
            tacheDto.setNom(tacheEntity.getNom());
            tacheDto.setService(tacheEntity.getService());
            tacheDto.setDate_debut(tacheEntity.getDate_debut());
            tacheDto.setDate_fin(tacheEntity.getDate_fin());
            tacheDto.setStatus(tacheEntity.getStatus());
            tacheDto.setDescription(tacheEntity.getDescription());
            tacheDto.setEmployeDto(EmployeDto.entityToDto(tacheEntity.getEmployeEntity()));
        }
        return tacheDto;
    }

    /**
     * @param tacheDto
     * @return
     */
    public static TacheEntity dtoToEntity(TacheDto tacheDto) {
        TacheEntity tacheEntity = new TacheEntity();
        if (tacheDto != null) {
            tacheEntity.setId(tacheDto.getId());
            tacheEntity.setReference(tacheDto.getReference());
            tacheEntity.setNom(tacheDto.getNom());
            tacheEntity.setService(tacheDto.getService());
            tacheEntity.setDate_debut(tacheDto.getDate_debut());
            tacheEntity.setDate_fin(tacheDto.getDate_fin());
            tacheEntity.setStatus(tacheDto.getStatus());
            tacheEntity.setDescription(tacheDto.getDescription());
            tacheEntity.setEmployeEntity(EmployeDto.dtoToEntity(tacheDto.getEmployeDto()));
        }
        return tacheEntity;
    }

    /**
     * @param tacheEntities
     * @return
     */
    public static List<TacheDto> entitiesToDtos(List<TacheEntity> tacheEntities) {
        List<TacheDto> tacheDtos = new ArrayList<>();
        if (!tacheEntities.isEmpty()) {
            tacheDtos = tacheEntities.stream().map(TacheDto::entityToDto).collect(Collectors.toList());
        }
        return tacheDtos;
    }
}
