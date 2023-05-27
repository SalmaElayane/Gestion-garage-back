package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.INotificationDao;
import com.cigma.gg.dao.backoffice.IUtilisateurDao;

import com.cigma.gg.entity.backoffice.MaintenenceEntity;
import com.cigma.gg.entity.backoffice.NotificationEntity;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class NotificationServiceImp implements INotificationService {
    @Autowired
    INotificationDao notificationDao;

    @Autowired
    IUtilisateurDao utilisateurDao;

    @Autowired
    private EmailService emailService;

    //envoye
    @Override
    public SaveResponseDto save(NotificationEntity notificationEntity) {
        SaveResponseDto saveResponseDto = null;
        final UtilisateurEntity utilisateurEntity;
        if (notificationEntity.getUtilisateurEntity() != null) {
            utilisateurEntity = utilisateurDao.findByEmail(notificationEntity.getUtilisateurEntity().getEmail());
            notificationEntity.setUtilisateurEntity(utilisateurEntity);
        }
        NotificationEntity checkRef = notificationDao.findByReference(notificationEntity.getReference());
        if (checkRef == null) {
            notificationDao.save(notificationEntity);
            saveResponseDto = new SaveResponseDto("done");
            //envoie du l'email
            emailService.sendMail(notificationEntity.getUtilisateurEntity().getEmail(),
                    notificationEntity.getObjet(),
                    notificationEntity.getMessage());
        } else {
            throw new SaveOrUpdateUserException("Référence déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param notificationEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(NotificationEntity notificationEntity) {
        NotificationEntity checkCin = notificationDao.findByReference(notificationEntity.getReference());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public NotificationEntity modify(NotificationEntity newUser, Long id) {
        NotificationEntity oldUser = notificationDao.findById(id).orElseThrow(NoSuchElementException::new);;
        boolean canUpdate = checkRefIsAlreadyExist(oldUser, newUser);
        NotificationEntity notificationEntity = null;
        if (canUpdate) {
            oldUser.setReference(newUser.getReference());
            oldUser.setMessage(newUser.getMessage());
            oldUser.setDateNotification(newUser.getDateNotification());
            oldUser.setObjet(newUser.getObjet());
            notificationEntity = notificationDao.save(oldUser);
            //envoie du l'email
            emailService.sendMail(notificationEntity.getUtilisateurEntity().getEmail(),
                    notificationEntity.getObjet(),
                    notificationEntity.getMessage());
        }
        return notificationEntity;
    }


    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(NotificationEntity oldUser, NotificationEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Référence déja existe!! ");
        }
    }


    //AFFICHER
    @Override
    public List<NotificationEntity> getAll() {
        return (List<NotificationEntity>) notificationDao.findAll();
    }

    @Override
    public List<NotificationEntity> find(String notification) {
        return notificationDao.findByReferenceContaining(notification);
    }

    //CHERCHER
    public List<NotificationEntity> Find(String notif) {
        return (List<NotificationEntity>)notificationDao.findByReference(notif);
    }

    //CHERCHER PAR ID
    @Override
    public NotificationEntity getOne(long idnotif) {
        return notificationDao.findById(idnotif).orElseThrow(NoSuchElementException::new);
    }
    @Override
    public List<NotificationEntity> getAllNotificationByReferenceKeyWord(String reference) {
        return notificationDao.findAllWhereReferenceLike(reference);
    }

    @Override
    public List<NotificationEntity> getAllNotificationReference() {
        return notificationDao.findAll();
    }

}
