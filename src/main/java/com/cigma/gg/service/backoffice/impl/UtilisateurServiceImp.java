package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IUtilisateurDao;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IUtilisateurService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class UtilisateurServiceImp implements IUtilisateurService {
    @Autowired
    IUtilisateurDao utilisateurDao;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    //AJOUTER
    @Override
    public SaveResponseDto save(UtilisateurEntity ult) {
        SaveResponseDto saveResponseDto = null;
        UtilisateurEntity checkCin = utilisateurDao.findByCin(ult.getCin());
        if (checkCin == null) {
            ult.setMot_de_passe(bcryptEncoder.encode(ult.getMot_de_passe()));
            utilisateurDao.save(ult);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Cin déjà existe !! vous devez utiliser un autre…");
        }
        return saveResponseDto;

    }

    /**
     * return true if cin already exists
     *
     * @param ult
     * @return
     */
    private Boolean checkIfCinExisteForSave(UtilisateurEntity ult) {
        UtilisateurEntity checkCin = utilisateurDao.findByCin(ult.getCin());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public UtilisateurEntity modify(UtilisateurEntity newUser, Long id) {
        UtilisateurEntity oldUser = utilisateurDao.findById(id).orElseThrow(NoSuchElementException::new);
        boolean canUpdate = checkCinIsAlreadyExist(oldUser, newUser);
        UtilisateurEntity utlisateurmodifier = null;
        if (canUpdate) {
            oldUser.setNom(newUser.getNom());
            oldUser.setPrenom(newUser.getPrenom());
            oldUser.setCin(newUser.getCin());
            oldUser.setEmail(newUser.getEmail());
            oldUser.setTele(newUser.getTele());
            oldUser.setRole(newUser.getRole());
            oldUser.setLogin(newUser.getLogin());
            oldUser.setMot_de_passe(newUser.getMot_de_passe());
            utlisateurmodifier = utilisateurDao.save(oldUser);
        }
        return utlisateurmodifier;
    }

    /**
     * check Cin Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkCinIsAlreadyExist(UtilisateurEntity oldUser, UtilisateurEntity newUser) {
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
        utilisateurDao.deleteById(idClt);
    }


    //AFFICHER
    @Override
    public List<UtilisateurEntity> getAll() {
        List<UtilisateurEntity> utilisateurEntities = utilisateurDao.findAll();
        return utilisateurEntities;
    }

    //CHERCHER
    @Override
    public List<UtilisateurEntity> find(String ult) {

        return utilisateurDao.findAllWhereCinLike(ult);
    }

    //CHERCHER PAR ID
    @Override
    public UtilisateurEntity getOne(long idUlt) {
        return utilisateurDao.findById(idUlt).get();
    }

    @Override
    public List<UtilisateurEntity> getAllUtilisateurByCinKeyWord(String cin) {
        return utilisateurDao.findAllWhereCinLike(cin);
    }

    @Override
    public List<UtilisateurEntity> getAllUtilisateurByEmailKeyWord(String email) {
        return utilisateurDao.findAllWhereEmailLike(email);
    }

    @Override
    public List<UtilisateurEntity> getAllUtilisateurCin() {
        return utilisateurDao.findAll();
    }


    @Override
    public List<UtilisateurEntity> getAllUtilisateurEmail() {
        return utilisateurDao.findAll();
    }
}
