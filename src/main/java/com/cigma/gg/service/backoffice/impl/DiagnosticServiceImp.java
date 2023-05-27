package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IDiagnostic;
import com.cigma.gg.entity.backoffice.DiagnosticEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IDiagnosticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class DiagnosticServiceImp implements IDiagnosticService {
    @Autowired
    IDiagnostic diagnosticDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(DiagnosticEntity diagnosticEntity) {
        SaveResponseDto saveResponseDto = null;
        DiagnosticEntity checkRef = diagnosticDao.findByReference(diagnosticEntity.getReference());
        if (checkRef == null) {
            diagnosticDao.save(diagnosticEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     * @param diagnosticEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(DiagnosticEntity diagnosticEntity)
    {
        DiagnosticEntity checkCin = diagnosticDao.findByReference(diagnosticEntity.getReference());
        return  checkCin!=null;
    }
    //MODIFIER
    @Override
    @Transactional
    public DiagnosticEntity modify(DiagnosticEntity newUser,Long id) {
        DiagnosticEntity oldUser = diagnosticDao.findById(id).get();
        boolean canUpdate=checkRefIsAlreadyExist(oldUser,newUser);
        DiagnosticEntity diagmodifier=null;
        if(canUpdate) {
            oldUser.setReference(newUser.getReference());
            oldUser.setLibelle(newUser.getLibelle());
            oldUser.setPrix(newUser.getPrix());
            oldUser.setDuree(newUser.getDuree());
            diagmodifier=diagnosticDao.save(oldUser);
        }
        return diagmodifier;
    }

    /**
     * check Reference Is Already Exist
     * @param oldUser
     * @param newUser
     * @return
     */
    private  Boolean checkRefIsAlreadyExist(DiagnosticEntity oldUser,DiagnosticEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
    }

    //SUPPRIMER
    @Override
    @Transactional
    public void remove(long iddiag) {
        diagnosticDao.deleteById(iddiag);
    }


    //AFFICHER
    @Override
    public List<DiagnosticEntity> getAll() {
        return (List<DiagnosticEntity>) diagnosticDao.findAll();
    }

    //CHERCHER
    public DiagnosticEntity Find(String diag) {
        return diagnosticDao.findByReference(diag);
    }

    //CHERCHER PAR ID
    @Override
    public DiagnosticEntity getOne(long iddiag) {
        return diagnosticDao.findById(iddiag).get();
    }
}
