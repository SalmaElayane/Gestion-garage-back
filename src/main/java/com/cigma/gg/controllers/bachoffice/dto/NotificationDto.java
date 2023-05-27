package com.cigma.gg.controllers.bachoffice.dto;

import com.cigma.gg.entity.backoffice.NotificationEntity;

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
public class NotificationDto implements Serializable {
    private Long id;
    private String reference;
    @JsonFormat(pattern="yyyy-mm-dd")
    private Date dateNotification;
    private String objet;
    private String message;
    private UtilisateurDto utilisateurDto;

    /**
     * @param notificationEntity
     * @return
     */
    public static NotificationDto entityToDto(NotificationEntity notificationEntity) {
        NotificationDto notificationDtoDto = new NotificationDto();
        if (notificationEntity != null) {
            notificationDtoDto.setId(notificationEntity.getId());
            notificationDtoDto.setReference(notificationEntity.getReference());
            notificationDtoDto.setDateNotification(notificationEntity.getDateNotification());
            notificationDtoDto.setMessage(notificationEntity.getMessage());
            notificationDtoDto.setObjet(notificationEntity.getObjet());
            notificationDtoDto.setUtilisateurDto(UtilisateurDto.entityToDto(notificationEntity.getUtilisateurEntity()));
        }
        return notificationDtoDto;
    }

    /**
     * @param notificationDto
     * @return
     */
    public static NotificationEntity dtoToEntity(NotificationDto notificationDto) {
        NotificationEntity notificationEntity = new NotificationEntity();
        if (notificationDto != null) {
            notificationEntity.setId(notificationDto.getId());
            notificationEntity.setReference(notificationDto.getReference());
            notificationEntity.setDateNotification(notificationDto.getDateNotification());
            notificationEntity.setMessage(notificationDto.getMessage());
            notificationEntity.setObjet(notificationDto.getObjet());
            notificationEntity.setUtilisateurEntity(UtilisateurDto.dtoToEntity(notificationDto.getUtilisateurDto()));
        }
        return notificationEntity;
    }

    /**
     * @param notificationEntities
     * @return
     */
    public static List<NotificationDto> entitiesToDtos(List<NotificationEntity> notificationEntities) {
        List<NotificationDto> notificationDtos = new ArrayList<>();
        if (!notificationEntities.isEmpty()) {
            notificationDtos = notificationEntities.stream().map(NotificationDto::entityToDto).collect(Collectors.toList());
        }
        return notificationDtos;
    }
}
