package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IMaintenenceDao;

import com.cigma.gg.dao.backoffice.IVoitureDao;
import com.cigma.gg.entity.backoffice.MaintenenceEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IMaintenenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class MaintenenceServiceImp implements IMaintenenceService {
    @Autowired
    IMaintenenceDao maintenenceDao;

    @Autowired
    IVoitureDao voitureDao;


    //AJOUTER
    @Override
    public SaveResponseDto save(MaintenenceEntity maintenenceEntity) {
        SaveResponseDto saveResponseDto = null;
        final VoitureEntity voitureEntity;
        if (maintenenceEntity.getVoitureEntity()!= null) {
            voitureEntity = voitureDao.findByMatricule(maintenenceEntity.getVoitureEntity().getMatricule());
            maintenenceEntity.setVoitureEntity(voitureEntity);
        }
        MaintenenceEntity checkRef = maintenenceDao.findByReference(maintenenceEntity.getReference());
        if (checkRef == null) {
            maintenenceDao.save(maintenenceEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param maintenenceEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(MaintenenceEntity maintenenceEntity) {
        MaintenenceEntity checkCin = maintenenceDao.findByReference(maintenenceEntity.getReference());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public MaintenenceEntity modify(MaintenenceEntity newMaintenenceEntity, Long id) {
        MaintenenceEntity maintenenceEntity = maintenenceDao.findById(id).orElseThrow(NoSuchElementException::new);
        boolean canUpdate = checkRefIsAlreadyExist(maintenenceEntity, newMaintenenceEntity);
        MaintenenceEntity maintenence = null;
        if (canUpdate) {
            maintenenceEntity.setReference(newMaintenenceEntity.getReference());
            maintenenceEntity.setDateDebut(newMaintenenceEntity.getDateDebut());
            maintenenceEntity.setDateFin(newMaintenenceEntity.getDateFin());
            maintenenceEntity.setEtat(newMaintenenceEntity.getEtat());
            maintenenceEntity.setSuivi(newMaintenenceEntity.getSuivi());
            maintenenceEntity.setDescription(newMaintenenceEntity.getDescription());
            VoitureEntity voitureEntity = voitureDao.findByMatricule(newMaintenenceEntity.getVoitureEntity().getMatricule());
            maintenenceEntity.setVoitureEntity(voitureEntity);
            maintenence = maintenenceDao.save(maintenenceEntity);
        }
        return maintenence;
    }

    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(MaintenenceEntity oldUser, MaintenenceEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Refrerence déja existe!! ");
        }
    }

    //SUPPRIMER
    @Override
    @Transactional
    public void remove(long idmaint) {
        maintenenceDao.deleteById(idmaint);
    }


    //AFFICHER
    @Override
    public List<MaintenenceEntity> getAll() {
        List<MaintenenceEntity> maintenenceEntities = maintenenceDao.findAll();
        return maintenenceEntities;
    }

    //CHERCHER
    public List<MaintenenceEntity> find(String maint) {
        return maintenenceDao.findByReferenceContaining(maint);
    }

    //CHERCHER PAR ID
    @Override
    public MaintenenceEntity getOne(long idmaint) {
        return maintenenceDao.findById(idmaint).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<MaintenenceEntity>  getAllMaintenanceByRefAndMatKeyWord(String reference) {
        return maintenenceDao.findAllWhereReferenceLike(reference);
    }

    @Override
    public List<MaintenenceEntity> getAllMaintenenceReference() {
        return maintenenceDao.findAll();
    }
}
