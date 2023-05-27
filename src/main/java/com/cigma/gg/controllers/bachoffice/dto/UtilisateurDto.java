package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.UtilisateurEntity;

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
public class UtilisateurDto implements Serializable {

    private Long id;
    private String nom;
    private String prenom;
    private String cin;
    private String email;
    private String tele;
    private String role;
    private String login;
    private String mot_de_passe;

    /**
     * @param utilisateurEntity
     * @return
     */
    public static UtilisateurDto entityToDto(UtilisateurEntity utilisateurEntity) {
        UtilisateurDto utilisateurDto = new UtilisateurDto();
        if (utilisateurEntity != null) {
            utilisateurDto.setId(utilisateurEntity.getId());
            utilisateurDto.setNom(utilisateurEntity.getNom());
            utilisateurDto.setPrenom(utilisateurEntity.getPrenom());
            utilisateurDto.setCin(utilisateurEntity.getCin());
            utilisateurDto.setTele(utilisateurEntity.getTele());
            utilisateurDto.setEmail(utilisateurEntity.getEmail());
            utilisateurDto.setRole(utilisateurEntity.getRole());
            utilisateurDto.setLogin(utilisateurEntity.getLogin());
            utilisateurDto.setMot_de_passe(utilisateurEntity.getMot_de_passe());
        }
        return utilisateurDto;
    }

    /**
     * @param utilisateurDto
     * @return
     */
    public static UtilisateurEntity dtoToEntity(UtilisateurDto utilisateurDto) {
        UtilisateurEntity utilisateurEntity = new UtilisateurEntity();
        if (utilisateurDto != null) {
            utilisateurEntity.setId(utilisateurDto.getId());
            utilisateurEntity.setNom(utilisateurDto.getNom());
            utilisateurEntity.setPrenom(utilisateurDto.getPrenom());
            utilisateurEntity.setCin(utilisateurDto.getCin());
            utilisateurEntity.setTele(utilisateurDto.getTele());
            utilisateurEntity.setEmail(utilisateurDto.getEmail());
            utilisateurEntity.setRole(utilisateurDto.getRole());
            utilisateurEntity.setLogin(utilisateurDto.getLogin());
            utilisateurEntity.setMot_de_passe(utilisateurDto.getMot_de_passe());
        }
        return utilisateurEntity;
    }

    /**
     * @param utilisateurEntities
     * @return
     */
    public static List<UtilisateurDto> entitiesToDtos(List<UtilisateurEntity> utilisateurEntities) {
        List<UtilisateurDto> utilisateurDtos = new ArrayList<>();
        if (!utilisateurEntities.isEmpty()) {
            utilisateurDtos = utilisateurEntities.stream().map(UtilisateurDto::entityToDto).collect(Collectors.toList());
        }
        return utilisateurDtos;
    }
}
