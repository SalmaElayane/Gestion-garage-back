package com.cigma.gg.service.backoffice;

import com.cigma.gg.controllers.bachoffice.dto.SaveResponseDto;

import com.cigma.gg.entity.backoffice.NotificationEntity;
import com.cigma.gg.entity.backoffice.VoitureEntity;


import java.util.List;

public interface IVoitureService {
    //AJOUTER
    SaveResponseDto save(VoitureEntity voiture);

    //MODFIER
    VoitureEntity modify(VoitureEntity voitureEntity, Long idvoit);

    //SUPPRIMER
    void remove(long idvoit);

    //AFFICHER
    List<VoitureEntity> getAll();

    //CHERCHER par reference
    List<VoitureEntity> find(String voit);

    //CHERCHER PAR ID
    VoitureEntity getOne(long voit);
    /**
     * @param matricule
     * @return
     */
    List<VoitureEntity> getAllVoitureByMatriculeKeyWord(String matricule);

    /**
     *
     * @return
     */
    List<VoitureEntity> getAllVoitureMatricule();

}
