package com.cigma.gg.service.backoffice.impl;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;
import com.cigma.gg.dao.backoffice.IRendezVousDao;
import com.cigma.gg.dao.backoffice.IUtilisateurDao;
import com.cigma.gg.entity.backoffice.NotificationEntity;
import com.cigma.gg.entity.backoffice.RendezVousEntity;
import com.cigma.gg.entity.backoffice.UtilisateurEntity;
import com.cigma.gg.enums.StatutEnum;
import com.cigma.gg.exceptions.SaveOrUpdateUserException;
import com.cigma.gg.service.backoffice.IRendezVousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class RendezVousServiceImp implements IRendezVousService {
    @Autowired
    IRendezVousDao rendezVousDao;

    @Autowired
    IUtilisateurDao utilisateurDao;

    //AJOUTER
    @Override
    public SaveResponseDto save(RendezVousEntity rendezVousEntity) {
        SaveResponseDto saveResponseDto = null;
        final UtilisateurEntity utilisateurEntity;
        if (rendezVousEntity.getUtilisateurEntity() != null) {
            utilisateurEntity = utilisateurDao.findByCin(rendezVousEntity.getUtilisateurEntity().getCin());
            rendezVousEntity.setUtilisateurEntity(utilisateurEntity);
        }
        RendezVousEntity checkRef = rendezVousDao.findByReference(rendezVousEntity.getReference());
        if (checkRef == null) {
            rendezVousEntity.setStatut(StatutEnum.EN_ATTENDANT.getCode());
            rendezVousDao.save(rendezVousEntity);
            saveResponseDto = new SaveResponseDto("done");
        } else {
            throw new SaveOrUpdateUserException("Référence déja existe!! ");
        }
        return saveResponseDto;

    }

    /**
     * return true if reference already exists
     *
     * @param rendezVousEntity
     * @return
     */
    private Boolean checkIfRefExisteForSave(RendezVousEntity rendezVousEntity) {
        RendezVousEntity checkCin = rendezVousDao.findByReference(rendezVousEntity.getReference());
        return checkCin != null;
    }

    //MODIFIER
    @Override
    @Transactional
    public RendezVousEntity modify(RendezVousEntity newUser, Long id) {
        RendezVousEntity oldUser = rendezVousDao.findById(id).orElseThrow(NoSuchElementException::new);
        boolean canUpdate = checkRefIsAlreadyExist(oldUser, newUser);
        RendezVousEntity rendezVousMdifier = null;
        if (canUpdate) {
            oldUser.setReference(newUser.getReference());
            oldUser.setType(newUser.getType());
            oldUser.setStatut(newUser.getStatut());
            oldUser.setDateRendezVous(newUser.getDateRendezVous());
            oldUser.setDescription(newUser.getDescription());
            rendezVousMdifier = rendezVousDao.save(oldUser);
        }
        return rendezVousMdifier;
    }

    @Override
    public RendezVousEntity valider(Long idrendezVous) {
        RendezVousEntity rendezVousEntity = rendezVousDao.findById(idrendezVous).orElseThrow(NoSuchElementException::new);
        rendezVousEntity.setStatut(StatutEnum.VALIDEE.getCode());
        rendezVousDao.save(rendezVousEntity);
        return rendezVousEntity;
    }

    @Override
    public RendezVousEntity refuser(Long idrendezVous) {
        RendezVousEntity rendezVousEntity = rendezVousDao.findById(idrendezVous).orElseThrow(NoSuchElementException::new);
        rendezVousEntity.setStatut(StatutEnum.REFUSEE.getCode());
        rendezVousDao.save(rendezVousEntity);
        return rendezVousEntity;
    }

    /**
     * check Reference Is Already Exist
     *
     * @param oldUser
     * @param newUser
     * @return
     */
    private Boolean checkRefIsAlreadyExist(RendezVousEntity oldUser, RendezVousEntity newUser) {
        if (oldUser.getReference().equals(newUser.getReference())) {
            return true;
        } else if (!oldUser.getReference().equals(newUser.getReference()) && !checkIfRefExisteForSave(newUser)) {
            return true;
        } else {
            throw new SaveOrUpdateUserException("Référence déja existe dans un autre rendez-vous!! ");
        }
    }

    //SUPPRIMER
    @Override
    @Transactional
    public void remove(long idrv) {
        rendezVousDao.deleteById(idrv);
    }

    @Override
    public List<RendezVousEntity> getAll(String statut) {
        if (statut.isBlank() || statut.isEmpty()) {
            return rendezVousDao.findAllByStatut(StatutEnum.EN_ATTENDANT.getCode());
        }
        return rendezVousDao.findAllByStatut(statut);
    }

    @Override
    public RendezVousEntity getOne(long idRv) {
        return rendezVousDao.findById(idRv).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<RendezVousEntity> getAllRendezVousByReferenceKeyWord(String reference) {
        return rendezVousDao.findAllWhereReferenceLike(reference);
    }

    @Override
    public List<RendezVousEntity> getAllRendezVousReference() {
        return rendezVousDao.findAll();
    }

    @Override
    public List<RendezVousEntity> find(String reference) {

        return rendezVousDao.findAllWhereReferenceLike(reference);
    }
}
