package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IEmployeDao;
import com.cigma.gg.entity.backoffice.EmployeEntity;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IEmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeServiceImp implements IEmployeService {
    @Autowired
    IEmployeDao employeDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(EmployeEntity empl) {
        SaveResponseDto saveResponseDto = null;
        EmployeEntity checkCin = employeDao.findByCin(empl.getCin());
        if (checkCin == null) {
            employeDao.save(empl);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Cin déja existe!! ");
        }
        return saveResponseDto;
    }

    /**
     * return true if cin already exists
     *
     * @param ult
     * @return
     */
    private Boolean checkIfCinExisteForSave(EmployeEntity ult) {
        EmployeEntity checkCin = employeDao.findByCin(ult.getCin());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public EmployeEntity modify(EmployeEntity newUser, Long id) {
        EmployeEntity oldUser = employeDao.findById(id).get();
        boolean canUpdate = checkCinIsAlreadyExist(oldUser, newUser);
        EmployeEntity employemodifier = null;
        if (canUpdate) {
            oldUser.setNom(newUser.getNom());
            oldUser.setPrenom(newUser.getPrenom());
            oldUser.setCin(newUser.getCin());
            oldUser.setAge(newUser.getAge());
            oldUser.setTele(newUser.getTele());
            oldUser.setRole(newUser.getRole());
            oldUser.setLogin(newUser.getLogin());
            oldUser.setMot_de_passe(newUser.getMot_de_passe());
            employemodifier = employeDao.save(oldUser);
        }
        return employemodifier;
    }

    /**
     * check Cin Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkCinIsAlreadyExist(EmployeEntity oldUser, EmployeEntity newUser) {
        if (oldUser.getCin().equals(newUser.getCin())) {
            return true;
        } else if (!oldUser.getCin().equals(newUser.getCin()) && !checkIfCinExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Cin déja existe!! ");
        }
    }

    //SUPPRIMER
    @Override
    @Transactional
    public void remove(long idClt) {
        employeDao.deleteById(idClt);
    }


    //AFFICHER
    @Override
    public List<EmployeEntity> getAll() {
        return (List<EmployeEntity>) employeDao.findAll();
    }

    //CHERCHER
    public List<EmployeEntity>  Find(String ult) {
        return employeDao.findAllWhereCinLike(ult);
    }


    //CHERCHER PAR ID
    @Override
    public EmployeEntity getOne(long idUlt) {
        return employeDao.findById(idUlt).get();
    }

    @Override
    public List<EmployeEntity> getAllUtilisateurByCinKeyWord(String cin) {
        return employeDao.findAllWhereCinLike(cin);
    }

    @Override
    public List<EmployeEntity> getAllEmployeCin() {
        return employeDao.findAll();
    }

}
