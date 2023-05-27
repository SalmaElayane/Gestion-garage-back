package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.entity.backoffice.NotificationEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;

import java.util.List;

public interface INotificationService {
    //AJOUTER
    SaveResponseDto save(NotificationEntity notification);

    //MODFIER
    NotificationEntity modify(NotificationEntity notificationEntity, Long idnotification);

    //AFFICHER
    List<NotificationEntity> getAll();

    //CHERCHER par reference
    List<NotificationEntity> find(String notification);

    //CHERCHER PAR ID
    NotificationEntity getOne(long notification);
    /**
     * @param refrenece
     * @return
     */
    List<NotificationEntity> getAllNotificationByReferenceKeyWord(String refrenece);

    /**
     *
     * @return
     */
    List<NotificationEntity> getAllNotificationReference();
}
