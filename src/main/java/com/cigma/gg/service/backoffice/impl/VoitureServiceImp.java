package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IUtilisateurDao;
import com.cigma.gg.dao.backoffice.IVoitureDao;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IVoitureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class VoitureServiceImp implements IVoitureService {

    @Autowired
    IVoitureDao voitureDao;

    @Autowired
    IUtilisateurDao utilisateurDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(VoitureEntity voitureEntity) {
        SaveResponseDto saveResponseDto = null;
        final UtilisateurEntity utilisateurEntity;
        if (voitureEntity.getUtilisateurEntity() != null) {
            utilisateurEntity = utilisateurDao.findByCin(voitureEntity.getUtilisateurEntity().getCin());
            voitureEntity.setUtilisateurEntity(utilisateurEntity);
        }
        VoitureEntity checkRef = voitureDao.findByMatricule(voitureEntity.getMatricule());
        if (checkRef == null) {
            voitureDao.save(voitureEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Matricule déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param voitureEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(VoitureEntity voitureEntity) {
        VoitureEntity checkCin = voitureDao.findByMatricule(voitureEntity.getMatricule());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public VoitureEntity modify(VoitureEntity newUser, Long id) {
        VoitureEntity oldUser = voitureDao.findById(id).orElseThrow(NoSuchElementException::new);
        boolean canUpdate = checkRefIsAlreadyExist(oldUser, newUser);
        VoitureEntity voituredifier = null;
        if (canUpdate) {
            oldUser.setMatricule(newUser.getMatricule());
            oldUser.setMarque(newUser.getMarque());
            oldUser.setModel(newUser.getModel());
            oldUser.setType(newUser.getType());
            oldUser.setPhoto(newUser.getPhoto());
            voituredifier = voitureDao.save(oldUser);
        }
        return voituredifier;
    }

    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(VoitureEntity oldUser, VoitureEntity newUser) {
        if (oldUser.getMatricule().equals(newUser.getMatricule())) {
            return true;
        } else if (!oldUser.getMatricule().equals(newUser.getMatricule()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Matricule déja existe!! ");
        }
    }

    //SUPPRIMER
    @Override
    @Transactional
    public void remove(long idvoit) {
        voitureDao.deleteById(idvoit);
    }

    @Override
    public List<VoitureEntity> getAll() {
        return (List<VoitureEntity>) voitureDao.findAll();
    }

    @Override
    public List<VoitureEntity> find(String diag) {
        return voitureDao.findAllWhereMatriculeLike(diag);
    }

    @Override
    public VoitureEntity getOne(long idVoit) {
        return voitureDao.findById(idVoit).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<VoitureEntity> getAllVoitureByMatriculeKeyWord(String matricule) {
        return voitureDao.findAllWhereMatriculeLike(matricule);
    }

    @Override
    public List<VoitureEntity> getAllVoitureMatricule() {
        return voitureDao.findAll();
    }
}
