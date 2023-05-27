package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.RendezVousEntity;
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
public class RendezVousDto implements Serializable{
    private Long id;
    private String reference;
    private String type;
    @JsonFormat(pattern = "yyyy-mm-dd")
    private Date dateRendezVous;
    private String description;
    private String statut;
    private UtilisateurDto utilisateurDto;

    /**
     * @param rendezVousEntity
     * @return
     */
    public static RendezVousDto entityToDto(RendezVousEntity rendezVousEntity) {
        RendezVousDto rendezVousDto = new RendezVousDto();
        if (rendezVousEntity != null) {
            rendezVousDto.setId(rendezVousEntity.getId());
            rendezVousDto.setReference(rendezVousEntity.getReference());
            rendezVousDto.setType(rendezVousEntity.getType());
            rendezVousDto.setDateRendezVous(rendezVousEntity.getDateRendezVous());
            rendezVousDto.setStatut(rendezVousEntity.getStatut());
            rendezVousDto.setDescription(rendezVousEntity.getDescription());
            rendezVousDto.setUtilisateurDto(UtilisateurDto.entityToDto(rendezVousEntity.getUtilisateurEntity()));
        }
        return rendezVousDto;
    }

    /**
     * @param rendezVousDto
     * @return
     */
    public static RendezVousEntity dtoToEntity(RendezVousDto rendezVousDto) {
        RendezVousEntity rendezVousEntity = new RendezVousEntity();
        if (rendezVousDto != null) {
            rendezVousEntity.setId(rendezVousDto.getId());
            rendezVousEntity.setReference(rendezVousDto.getReference());
            rendezVousEntity.setType(rendezVousDto.getType());
            rendezVousEntity.setDateRendezVous(rendezVousDto.getDateRendezVous());
            rendezVousEntity.setStatut(rendezVousDto.getStatut());
            rendezVousEntity.setDescription(rendezVousDto.getDescription());
            rendezVousEntity.setUtilisateurEntity(UtilisateurDto.dtoToEntity(rendezVousDto.getUtilisateurDto()));
        }
        return rendezVousEntity;
    }

    /**
     * @param rendezVousEntities
     * @return
     */
    public static List<RendezVousDto> entitiesToDtos(List<RendezVousEntity> rendezVousEntities) {
        List<RendezVousDto> rendezVousDtos = new ArrayList<>();
        if (!rendezVousEntities.isEmpty()) {
            rendezVousDtos = rendezVousEntities.stream().map(RendezVousDto::entityToDto).collect(Collectors.toList());
        }
        return rendezVousDtos;
    }

}
