package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IEmployeDao;
import com.cigma.gg.dao.backoffice.ITacheDao;
import com.cigma.gg.entity.backoffice.EmployeEntity;
import com.cigma.gg.entity.backoffice.TacheEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.ITacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TacheServiceImp implements ITacheService {
    @Autowired
    ITacheDao tacheDao;

    @Autowired
    IEmployeDao employeDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(TacheEntity tacheEntity) {
        SaveResponseDto saveResponseDto = null;
        final EmployeEntity employeEntity;
        if (tacheEntity.getEmployeEntity() != null) {
            employeEntity = employeDao.findByCin(tacheEntity.getEmployeEntity().getCin());
            tacheEntity.setEmployeEntity(employeEntity);
        }
        TacheEntity checkRef = tacheDao.findByReference(tacheEntity.getReference());
        if (checkRef == null) {
            tacheDao.save(tacheEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param tacheEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(TacheEntity tacheEntity) {
        TacheEntity checkCin = tacheDao.findByReference(tacheEntity.getReference());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public TacheEntity modify(TacheEntity newUser, Long id) {
        TacheEntity oldUser = tacheDao.findById(id).get();
        boolean canUpdate = checkRefIsAlreadyExist(oldUser, newUser);
        TacheEntity tache = null;
        if (canUpdate) {
            oldUser.setReference(newUser.getReference());
            oldUser.setNom(newUser.getNom());
            oldUser.setService(newUser.getService());
            oldUser.setDate_debut(newUser.getDate_debut());
            oldUser.setDate_fin(newUser.getDate_fin());
            oldUser.setStatus(newUser.getStatus());
            oldUser.setDescription(newUser.getDescription());
            tache = tacheDao.save(oldUser);
        }
        return tache;
    }

    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(TacheEntity oldUser, TacheEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
    }

    @Override
    @Transactional
    public void remove(long idtache) {
        tacheDao.deleteById(idtache);
    }

    @Override
    public List<TacheEntity> getAll() {
        return (List<TacheEntity>) tacheDao.findAll();
    }

    @Override
    public List<TacheEntity> find(String diag) {
        return tacheDao.findByReferenceContaining(diag);
    }

    @Override
    public TacheEntity getOne(long idtache) {
        return tacheDao.findById(idtache).orElseThrow(NoSuchElementException::new);
    }
}
