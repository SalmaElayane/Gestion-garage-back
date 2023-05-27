package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;


import com.cigma.gg.dao.backoffice.IStockDao;
import com.cigma.gg.entity.backoffice.StockEntity;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockServiceImp implements IStockService {
    @Autowired
    IStockDao stockDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(StockEntity stock) {
        SaveResponseDto saveResponseDto = null;
        StockEntity checkRef = stockDao.findByReference(stock.getReference());
        if (checkRef == null) {
            stockDao.save(stock);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Reference déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     * @param stock
     * @return
     */
    private Boolean checkIfRefExisteForSave(StockEntity stock)
    {
        StockEntity checkCin = stockDao.findByReference(stock.getReference());
        return  checkCin!=null;
    }
    //MODIFIER
    @Override
    @Transactional
    public StockEntity modify(StockEntity newUser,Long id) {
        StockEntity oldUser = stockDao.findById(id).get();
        boolean canUpdate=checkRefIsAlreadyExist(oldUser,newUser);
        StockEntity stockmodifier=null;
        if(canUpdate) {
            oldUser.setReference(newUser.getReference());
            oldUser.setNom(newUser.getNom());
            oldUser.setCategorie(newUser.getCategorie());
            oldUser.setPrix(newUser.getPrix());
            oldUser.setQuantite(newUser.getQuantite());
            stockmodifier=stockDao.save(oldUser);
        }
        return stockmodifier;
    }

    /**
     * check Reference Is Already Exist
     * @param oldUser
     * @param newUser
     * @return
     */
    private  Boolean checkRefIsAlreadyExist(StockEntity oldUser,StockEntity newUser) {
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
    public void remove(long idStk) {
        stockDao.deleteById(idStk);
    }


    //AFFICHER
    @Override
    public List<StockEntity> getAll() {
        return (List<StockEntity>) stockDao.findAll();
    }

    //CHERCHER
    public StockEntity Find(String stock) {
        return stockDao.findByReference(stock);
    }

    //CHERCHER PAR ID
    @Override
    public StockEntity getOne(long idStk) {
        return stockDao.findById(idStk).get();
    }
}
