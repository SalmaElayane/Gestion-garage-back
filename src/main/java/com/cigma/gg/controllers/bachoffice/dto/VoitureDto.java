package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.VoitureEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VoitureDto implements Serializable {
    private Long id;
    private String matricule;
    private String marque;
    private String model;
    private String type;
    private byte[] photo;
    private UtilisateurDto utilisateurDto;

    /**
     * @param voitureEntity
     * @return
     */
    public static VoitureDto entityToDto(VoitureEntity voitureEntity) {
        VoitureDto voitureDto = new VoitureDto();
        if (voitureEntity != null) {
            voitureDto.setId(voitureEntity.getId());
            voitureDto.setMatricule(voitureEntity.getMatricule());
            voitureDto.setMarque(voitureEntity.getMarque());
            voitureDto.setModel(voitureEntity.getModel());
            voitureDto.setType(voitureEntity.getType());
            voitureDto.setPhoto(voitureEntity.getPhoto());
            voitureDto.setUtilisateurDto(UtilisateurDto.entityToDto(voitureEntity.getUtilisateurEntity()));
        }
        return voitureDto;
    }

    /**
     * @param voitureDto
     * @return
     */
    public static VoitureEntity dtoToEntity(VoitureDto voitureDto) {
        VoitureEntity voitureEntity = new VoitureEntity();
        if (voitureDto != null) {
            voitureEntity.setId(voitureDto.getId());
            voitureEntity.setMatricule(voitureDto.getMatricule());
            voitureEntity.setMarque(voitureDto.getMarque());
            voitureEntity.setModel(voitureDto.getModel());
            voitureEntity.setType(voitureDto.getType());
            voitureEntity.setPhoto(voitureDto.getPhoto());
            voitureEntity.setUtilisateurEntity(UtilisateurDto.dtoToEntity(voitureDto.getUtilisateurDto()));
        }
        return voitureEntity;
    }

    /**
     * @param voitureEntities
     * @return
     */
    public static List<VoitureDto> entitiesToDtos(List<VoitureEntity> voitureEntities) {
        List<VoitureDto> voitureDtos = new ArrayList<>();
        if (!voitureEntities.isEmpty()) {
            voitureDtos = voitureEntities.stream().map(VoitureDto::entityToDto).collect(Collectors.toList());
        }
        return voitureDtos;
    }

}
